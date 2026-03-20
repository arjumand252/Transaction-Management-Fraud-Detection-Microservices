package com.bankApp.jwtAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private BankUserDetailsService userDetailsService;
    
    @Autowired
    private AfterLoginFilter afterLoginFilter;
    
    
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                  AuthenticationManager authManager) throws Exception {

       JsonLoginFilter jsonLoginFilter = new JsonLoginFilter(authManager);

       http.cors(cors -> cors.disable())
           .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/auth/**", "/error").permitAll()
           		.requestMatchers(
           	            "/v1/login",
           	            "/swagger-ui/**",
           	            "/v3/api-docs/**",
           	            "/swagger-ui.html"
           	        ).permitAll()
                // .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
           		.requestMatchers("/v1/login").permitAll()
               .requestMatchers("/v1/managers/**").hasRole("MGR")
               .requestMatchers("/v1/Clerk/**").hasAnyRole("MGR", "CLERK")
               .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
               .anyRequest().authenticated()
           )
           .authenticationProvider(authenticationProvider())
           .addFilterAt(jsonLoginFilter, UsernamePasswordAuthenticationFilter.class)
           .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
           .addFilterAfter(afterLoginFilter, JwtAuthFilter.class)
           .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

       return http.build();
   }
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http,
    //                                                AuthenticationManager authManager) throws Exception {

    //     JsonLoginFilter jsonLoginFilter = new JsonLoginFilter(authManager);

    //     http
    //         .csrf(csrf -> csrf.disable())
    //         .sessionManagement(sess ->
    //                 sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

    //         .authorizeHttpRequests(auth -> auth

    //             // 🔓 Swagger first
    //             .requestMatchers(
    //                     "/swagger-ui/**",
    //                     "/v3/api-docs/**",
    //                     "/swagger-ui.html"
    //             ).permitAll()

    //             // 🔓 Login
    //             .requestMatchers("/v1/login").permitAll()

    //             // 🔒 Role based
    //             .requestMatchers("/mgr/**").hasRole("MGR")
    //             .requestMatchers("/Clerk/**").hasAnyRole("MGR", "CLERK")

    //             .anyRequest().authenticated()
    //         )

    //         .authenticationProvider(authenticationProvider())

    //         // JWT MUST be before UsernamePasswordAuthenticationFilter
    //         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

    //         // JSON login replaces default login
    //         .addFilterAt(jsonLoginFilter, UsernamePasswordAuthenticationFilter.class);

    //     return http.build();
    // }
}