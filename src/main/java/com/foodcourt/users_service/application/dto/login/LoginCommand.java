package com.foodcourt.users_service.application.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginCommand {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
