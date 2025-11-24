package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.GetRoleResponse;

public interface IAuthHandler {

    GetRoleResponse getRoleById(Long userId);

    //login
    //refresh
    //logout

}
