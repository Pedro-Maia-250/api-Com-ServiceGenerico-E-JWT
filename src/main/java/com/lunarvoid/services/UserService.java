package com.lunarvoid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lunarvoid.dto.RegistroUserDTOA;
import com.lunarvoid.dto.UserDTOA;
import com.lunarvoid.dto.exceptions.DTOAException;
import com.lunarvoid.entities.User;
import com.lunarvoid.enums.UserRoles;
import com.lunarvoid.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registrarCli(UserDTOA DTOA){
        if(repository.findByUsername(DTOA.username()).isPresent()){
            throw new DTOAException("esse nome de usuario já existe");
        }else{
            String encipString = new BCryptPasswordEncoder().encode(DTOA.password());
            repository.save(new User(DTOA.username(), encipString, UserRoles.CLI));
        }
    }

    public void registrar(RegistroUserDTOA DTOA){
        if(repository.findByUsername(DTOA.username()).isPresent()){
            throw new DTOAException("esse nome de usuario já existe");
        }else{
            String encipString = new BCryptPasswordEncoder().encode(DTOA.password());
            repository.save(new User(DTOA.username(), encipString, DTOA.roles()));
        }
    }
 
}
