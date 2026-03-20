package com.bankApp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.AuthRequest;
import com.bankApp.jwtAuth.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = JwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());

            return ResponseEntity.ok(Map.of("token", token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
    
	// private final JwtService jwtService;
	// private final AuthenticationManager authenticationManager;
	// private final UserDetailsService userDetailsService;

	// @PostMapping("/authenticate")
	// public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

	// 	Authentication authentication = authenticationManager.authenticate(
	// 			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	// 	if (authentication.isAuthenticated()) {
	// 		return jwtService.generateToken(userDetailsService.loadUserByUsername(authRequest.getUsername()));
	// 	} else {
	// 		throw new UsernameNotFoundException("Invalid user");
	// 	}
	// }

}
