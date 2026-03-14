package com.lunarvoid.dto;

import com.lunarvoid.enums.UserRoles;

public record RegistroUserDTOA(String username, String password, UserRoles roles) {
    
}
