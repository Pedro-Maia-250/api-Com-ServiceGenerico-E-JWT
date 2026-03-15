package com.lunarvoid.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunarvoid.dto.RegistroUserDTOA;
import com.lunarvoid.dto.TokenDTOR;
import com.lunarvoid.dto.UserDTOA;
import com.lunarvoid.services.LoginService;
import com.lunarvoid.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class UserResources {

    @Autowired
    private LoginService lservice;

    @Autowired
    private UserService service;


    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTOR> login(@RequestBody @Validated UserDTOA data){

        return ResponseEntity.ok().body(lservice.login(data));
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
