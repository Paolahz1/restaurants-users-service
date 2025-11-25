package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;

public interface IAuthHandler {

    AuthResponse login(LoginCommand command);

}
