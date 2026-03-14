package com.lunarvoid.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lunarvoid.enums.UserRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tb")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer role;

    public User(){}
    
    public User(String username, String password, UserRoles role) {
        this.username = username;
        this.password = password;
        this.role = role.getCode();
    }

    public void setRole(UserRoles role){
        this.role = role.getCode();
    }

    public UserRoles getRoles(){
        return UserRoles.valueOf(this.role);
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoles currentRoles = getRoles();

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        switch (currentRoles) {
            case UserRoles.ADMIN:
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.ADMIN.name()));
            case UserRoles.FUNC:
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.FUNC.name()));
            case UserRoles.CLI:
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.CLI.name()));
            break;
        }

        return list;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}
