package com.example.mywebsite.config;

import com.example.mywebsite.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableWebMvc
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( request -> request // Kiểm tra có cần xác thực không
                        .requestMatchers(
                                String.format("%s/user/register", apiPrefix),
                                String.format("%s/user/login", apiPrefix)

                        )
                        .permitAll()


                        .requestMatchers(GET,
                                String.format("%s/category/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(POST,
                                String.format("%s/category/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(PUT,
                                        String.format("%s/category/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(DELETE,
                                String.format("%s/category/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(GET,
                                String.format("%s/category/**", apiPrefix)
                        ).hasRole("USER")


                        .requestMatchers(GET,
                                String.format("%s/post/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(POST,
                                String.format("%s/post/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(PUT,
                                String.format("%s/post/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(DELETE,
                                String.format("%s/post/**", apiPrefix)
                        ).hasRole("USER")
                        .requestMatchers(GET,
                                String.format("%s/post/**", apiPrefix)
                        ).hasRole("USER")

                        
                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }
}
