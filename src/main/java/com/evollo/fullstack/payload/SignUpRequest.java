package com.evollo.fullstack.payload;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String username;
    private String password;

}
