package com.example.mywebsite;

import com.example.mywebsite.entity.Category;
import com.example.mywebsite.repo.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.Collection;

@SpringBootApplication
public class MyWebsiteApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyWebsiteApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run (CategoryRepo repo) {
//		return args -> {
//			repo.save(new Category(null, "Angular 1"));
//			repo.save(new Category(null, "Angular 2"));
//			repo.save(new Category(null, "Angular 3"));
//			repo.save(new Category(null, "Angular 4"));
//		};
//	}

//	@Bean
//	CommandLineRunner test(CategoryRepo repo){
//		SecurityContext context = SecurityContextHolder.createEmptyContext();
//		Authentication authentication =
//				new TestingAuthenticationToken("username", "password", "ROLEUSER");
//		context.setAuthentication(authentication);
//
//		SecurityContextHolder.setContext(context);
//
//
//
//		SecurityContext context1 = SecurityContextHolder.getContext();
//		Authentication authentication1 = context.getAuthentication();
//		String username = authentication1.getName();
//		Object principal = authentication1.getPrincipal();
//		Collection<? extends GrantedAuthority> authorities = authentication1.getAuthorities();
//
//		System.out.println("context1 : " + context1);
//		System.out.println("authentication1 : " + authentication1);
//		System.out.println("username : " + username);
//		System.out.println("principal : " + principal);
//		System.out.println("authorities : " + authorities);
//
//		return args -> {
//			repo.save(new Category(null, "Angular 1"));
//		};
//	}
}
