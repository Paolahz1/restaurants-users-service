package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.AuthResponse;
import com.foodcourt.users_service.application.dto.GetRoleResponse;
import com.foodcourt.users_service.application.dto.GetUserResponse;
import com.foodcourt.users_service.application.dto.LoginCommand;

public interface IAuthHandler {

    GetRoleResponse getRoleById(Long userId);
    AuthResponse login(LoginCommand command);
    GetUserResponse getUser(Long userId);
    //refresh
    //logout

}
