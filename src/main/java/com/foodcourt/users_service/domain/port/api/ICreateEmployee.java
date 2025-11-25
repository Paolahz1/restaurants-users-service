package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.User;

public interface ICreateEmployee {
    void saveEmployee (User employee);
}
