package com.foodcourt.users_service.domain.service;

import com.foodcourt.users_service.domain.exception.InvalidDocumentException;
import com.foodcourt.users_service.domain.exception.InvalidEmailFormatException;
import com.foodcourt.users_service.domain.exception.InvalidPhoneNumberException;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;

public class UserValidationService implements IUserValidationService {

    @Override
    public void validate(User user) {

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailFormatException();
        }
        if (!user.getPhoneNumber().matches("^\\+?\\d{1,13}$")) {
            throw new InvalidPhoneNumberException();
        }

        if (!user.getIdentityDocument().matches("\\d+")) {
            throw new InvalidDocumentException();
        }

    }
}

