package com.foodcourt.users_service.domain.port.spi;

import com.foodcourt.users_service.domain.model.User;

public interface IUserValidationService {

    public void validate(User user);

}
