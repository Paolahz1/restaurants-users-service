package com.foodcourt.users_service.domain.service;

import com.foodcourt.users_service.domain.exception.InvalidDocumentException;
import com.foodcourt.users_service.domain.exception.InvalidEmailFormatException;
import com.foodcourt.users_service.domain.exception.InvalidPhoneNumberException;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidationServiceTest {

    IUserValidationService validator;

    @BeforeEach
    void setup() {
        validator = new UserValidationService(); // implementación real
    }

    private User baseUser() {
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .email("test@mail.com")
                .phoneNumber("+573001112233")
                .identityDocument("123456789")
                .password("1234")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
    }

    @Test
    void shouldThrowWhenEmailIsInvalid() {
        User user = baseUser();
        user.setEmail("correo-invalido");

        assertThrows(InvalidEmailFormatException.class,
                () -> validator.validate(user));
    }


    @Test
    void shouldThrowWhenPhoneNumberIsInvalid() {
        User user = baseUser();
        user.setPhoneNumber("000573001112233");

        assertThrows(InvalidPhoneNumberException.class,
                () -> validator.validate(user));
    }


    @Test
    void shouldThrowWhenIdentityDocumentContainsLetters() {
        User user = baseUser();
        user.setIdentityDocument("ABC123");

        assertThrows(InvalidDocumentException.class,
                () -> validator.validate(user));
    }


    @Test
    void shouldThrowWhenIdentityDocumentIsEmpty() {
        User user = baseUser();
        user.setIdentityDocument("");

        assertThrows(InvalidDocumentException.class,
                () -> validator.validate(user));
    }
}
