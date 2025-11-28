package com.foodcourt.users_service.application.handler.port;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;

public interface IUserInfoHandler {

    GetRoleResponse getRoleById(Long userId);
    GetUserByIdResponse getUserById(Long userId);
    GetUserByEmailResponse getUserByEmail(String email);


}
