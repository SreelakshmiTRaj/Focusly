package com.example.Focusly.auth;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // 👈 Important to enable @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/studyplan/**",
                    "/studytask/**",
                    "/moodcheckin/**",
                    "/badge/**",
                    "/forum/**",
                    "/api/notifications/**",
                    "/recommendation/**",
                    "/search/**",
                    "/progress/**",
                    "/dashboard/**"
                ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // 👈 Protect admin endpoints
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Optional: If you're using username/password auth
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Optional: Password encoder if needed for user registration/login
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
