package com.foodcourt.users_service.application.dto.login;

import lombok.Data;

@Data
public class LoginCommand {
    private String email;
    private String password;

}
