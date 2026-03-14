package com.lunarvoid.enums;

public enum UserRoles {
    CLI(1),
    FUNC(2),
    ADMIN(3);
    
    private int code;

    private UserRoles(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static UserRoles valueOf(Integer code){
        for (UserRoles role : UserRoles.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Codigo de enum errado");
    }

}
