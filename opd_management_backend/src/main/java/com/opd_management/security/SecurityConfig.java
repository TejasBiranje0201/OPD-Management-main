package com.opd_management.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .exceptionHandling(ex -> 
            ex.authenticationEntryPoint(jwtAuthEntryPoint)
        )
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers( "/doctors/register",
	            					"/auth/login/doctor",
	            		 "/auth/login/admin",
	                     "/auth/login/reception").permitAll()
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> 
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        );

	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	    
	}
	
	
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	  http
//          .csrf(csrf -> csrf.disable())
//          .authorizeHttpRequests(auth -> auth
//              .requestMatchers(
//                  "/doctors/register",
//                  "/auth/login"
//              ).permitAll()
//              .anyRequest().authenticated()
//          )
//          .formLogin(form -> form.disable())   // ✅ FIX
//          .httpBasic(basic -> {});              // ✅ FIX
//
//      return http.build();
//    }
}