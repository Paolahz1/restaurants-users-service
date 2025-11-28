package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.User;

public interface ICreateClientServicePort {
    User create (User employee);
}
