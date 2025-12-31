package com.opd_management.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.opd_management.responce.ErrorResponce;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.security.core.userdetails.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;
@Component   
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	// MAIN FILTER METHOD
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);

                if (jwtUtil.isValid(token)) {
                    String email = jwtUtil.extractEmail(token);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            // Continue request
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException ex) {
            sendError(response, "JWT token expired");

        } catch (MalformedJwtException ex) {
            sendError(response, "Invalid JWT token");

        } catch (SignatureException ex) {
            sendError(response, "JWT signature invalid");

        } catch (Exception ex) {
            sendError(response, "JWT authentication failed");
        }
    }

    // 🟡 HELPER METHOD (WRITE THIS HERE 👇)
    private void sendError(HttpServletResponse response, String message)
            throws IOException {

        ErrorResponce error = new ErrorResponce(
                HttpServletResponse.SC_UNAUTHORIZED,
                message
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

}
