package com.lunarvoid.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lunarvoid.dto.exceptions.DTOAException;
import com.lunarvoid.repositories.UserRepository;
import com.lunarvoid.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService service;

    @Autowired
    UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null){
            String username = service.validateToken(token);
            UserDetails user = repository.findByUsername(username).orElseThrow(() ->  new DTOAException("token invalido"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null , user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
    
    private String recoverToken(HttpServletRequest request){
        var authReader = request.getHeader("Authorization");
        if(authReader != null){
            return authReader.replace("Bearer ", "");
        }else{
            return null;
        }
    }
}
