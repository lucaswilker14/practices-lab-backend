package com.evollo.fullstack.payload;

import com.evollo.fullstack.model.RoleName;
import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String username;
    private String password;
    private RoleName role;

    public SignUpRequest(String name, String username, String password, RoleName role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
