package com.plabs.backend.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Login Request")
@Data
public class LoginRequestModel {

    @ApiModelProperty(example = "admin")
    @NotBlank
    private String username;

    @ApiModelProperty(example = "'123'")
    @NotBlank
    private String password;
}
