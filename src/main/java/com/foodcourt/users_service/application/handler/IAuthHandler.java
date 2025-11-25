package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;

public interface IAuthHandler {

    GetRoleResponse getRoleById(Long userId);
    AuthResponse login(LoginCommand command);
    GetUserByIdResponse getUserById(Long userId);
    GetUserByEmailResponse getUserByEmail(String email);


}
