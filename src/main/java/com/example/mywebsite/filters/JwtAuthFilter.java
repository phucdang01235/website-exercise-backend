package com.example.mywebsite.filters;

import com.example.mywebsite.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import com.example.mywebsite.config.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
// Kiểm tra token có và hợp lệ không
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        try{
            if(isByPassToken(request)){
                filterChain.doFilter(request, response);
                return ;
            }

            final String authHeader = request.getHeader(AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
                response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "authHeader null or not started with Bearer");
                return ;
            }

            final String jwtToken = authHeader.substring(7);
            final String userEmail = jwtTokenUtil.getUsernameFromToken(jwtToken);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User userDetails = (User) userDetailsService.loadUserByUsername(userEmail);
                if( jwtTokenUtil.validateToken(jwtToken, userDetails) ){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            };
            filterChain.doFilter(request, response);


        }catch (Exception e) {
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(e.getMessage());
        }
    }


    private boolean isByPassToken( HttpServletRequest request){
        final List<Pair<String,String>> byPassToken = Arrays.asList(
                Pair.of(String.format("%s/user/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/user/register", apiPrefix), "POST"),
                Pair.of(String.format("%s/category/categories", apiPrefix), "GET")
                //Pair.of(String.format("%s/post/posts", apiPrefix), "GET")
        );

        for (Pair<String, String> token : byPassToken){
            String path = token.getFirst();
            String method = token.getSecond();
            if (request.getServletPath().contains(path) &&
                    request.getMethod().contains(method)){
                return true;
            }
        }
        return false;
    }

}
