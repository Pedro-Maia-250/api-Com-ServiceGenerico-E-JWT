package com.lunarvoid.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf((csrf) -> csrf.disable())
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(autorize -> autorize.requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll())
        .authorizeHttpRequests(autorize -> autorize.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/user").permitAll())
        .authorizeHttpRequests(autorize -> autorize.requestMatchers(HttpMethod.POST, "/circulos", "/quadrados", "/retangulos", "/auth/user/adm").hasRole("ADMIN"))
        .authorizeHttpRequests(autorize -> autorize.requestMatchers(HttpMethod.DELETE, "/circulos", "/quadrados", "/retangulos").hasRole("ADMIN"))
        .authorizeHttpRequests(autorize -> autorize.requestMatchers(HttpMethod.PUT, "/circulos", "/quadrados", "/retangulos").hasRole("FUNC"))
        .authorizeHttpRequests(autorize -> autorize.anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager gAuthenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
