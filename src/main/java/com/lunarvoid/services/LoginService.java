package com.lunarvoid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.TokenDTOR;
import com.lunarvoid.dto.UserDTOA;
import com.lunarvoid.entities.User;

@Service
public class LoginService {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private TokenService tokenService;

        public TokenDTOR login(UserDTOA data){
        UsernamePasswordAuthenticationToken usernameUser = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        User user = (User) authManager.authenticate(usernameUser).getPrincipal();
        String token = tokenService.generateToken(user);
        return new TokenDTOR(token);
    }
}
