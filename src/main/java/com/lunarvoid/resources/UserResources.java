package com.lunarvoid.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunarvoid.dto.RegistroUserDTOA;
import com.lunarvoid.dto.TokenDTOR;
import com.lunarvoid.dto.UserDTOA;
import com.lunarvoid.entities.User;
import com.lunarvoid.services.TokenService;
import com.lunarvoid.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class UserResources {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserDTOA data){
        UsernamePasswordAuthenticationToken usernameUser = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Object user = authManager.authenticate(usernameUser).getPrincipal();
        String token = tokenService.generateToken((User) user);

        return ResponseEntity.ok().body(new TokenDTOR(token));
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> register(@RequestBody UserDTOA data){
        service.registrarCli(data);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user/adm")
    public ResponseEntity<?> register(@RequestBody RegistroUserDTOA data){
        service.registrar(data);
        return ResponseEntity.ok().build();
    }
}
