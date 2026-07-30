package com.project.autoserve.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            JwtAccessDeniedHandler accessDeniedHandler) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))

                .authorizeHttpRequests(auth -> auth

                        // ===========================
                        // Public APIs
                        // ===========================
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // ===========================
                        // Mechanic Management
                        // ===========================
                        .requestMatchers("/api/mechanics/**")
                        .hasRole("ADMIN")

                        // ===========================
                        // Vehicle APIs
                        // ===========================
                        .requestMatchers(HttpMethod.GET, "/api/vehicles/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        .requestMatchers(HttpMethod.POST, "/api/vehicles/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        .requestMatchers(HttpMethod.PUT, "/api/vehicles/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        .requestMatchers(HttpMethod.DELETE, "/api/vehicles/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        // ===========================
                        // Appointment APIs
                        // ===========================
                        .requestMatchers(HttpMethod.GET, "/api/appointments/**")
                        .hasAnyRole("ADMIN", "CUSTOMER", "MECHANIC")

                        .requestMatchers(HttpMethod.POST, "/api/appointments/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        .requestMatchers(HttpMethod.PUT, "/api/appointments/**")
                        .hasAnyRole("ADMIN", "CUSTOMER", "MECHANIC")

                        .requestMatchers(HttpMethod.DELETE, "/api/appointments/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        // ===========================
                        // Job Card APIs
                        // ===========================
                        .requestMatchers(HttpMethod.GET, "/api/jobcards/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.POST, "/api/jobcards/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.PUT, "/api/jobcards/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.DELETE, "/api/jobcards/**")
                        .hasRole("ADMIN")

                        // ===========================
                        // Job Card Part APIs
                        // ===========================
                        .requestMatchers(HttpMethod.GET, "/api/jobcard-parts/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.POST, "/api/jobcard-parts/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.PUT, "/api/jobcard-parts/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.DELETE, "/api/jobcard-parts/**")
                        .hasRole("ADMIN")
                        
                        .requestMatchers(HttpMethod.POST,
                                "/api/invoices/generate/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        .requestMatchers(HttpMethod.GET,
                                "/api/invoices/**")
                        .hasAnyRole("ADMIN", "MECHANIC")

                        // ===========================
                        // Any other request
                        // ===========================
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

}