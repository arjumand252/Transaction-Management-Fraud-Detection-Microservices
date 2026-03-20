package com.bankApp.jwtAuth;
import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {

        public JsonLoginFilter(AuthenticationManager authenticationManager) {
			super(authenticationManager);
            setFilterProcessesUrl("/v1/login"); // important
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request,
                                                    HttpServletResponse response)
                throws AuthenticationException {

            try {
                Map<String, String> creds =
                        new ObjectMapper().readValue(request.getInputStream(), Map.class);

                String username = creds.get("username");
                String password = creds.get("password");

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, password);

                return getAuthenticationManager().authenticate(authToken);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        SecUser user = (SecUser) authResult.getPrincipal();

        // Generate JWT (implement JwtUtil.generateToken)
        String token = JwtUtil.generateToken(user.getUsername(), user.getAuthorities());

        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();
    }
}
