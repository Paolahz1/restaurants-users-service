package com.foodcourt.users_service.application.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCommand {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
