package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.*;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeUseCaseTest {


    @Mock
    IUserPersistencePort persistencePort;

    @InjectMocks
    CreateEmployeeUseCase useCase;

    @Test
    void shouldCreateEmployeeSuccessfully() {
        User testUser = User.builder()
                .email("employee@test.com")
                .identityDocument("123456")
                .phoneNumber("+573001112233")
                .build();

        when(persistencePort.getUserByeEmail(testUser.getEmail())).thenReturn(null);
        when(persistencePort.saveUser(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = useCase.create(testUser);

        assertEquals(Role.EMPLOYEE, result.getRole());
        verify(persistencePort).saveUser(argThat(user ->
                user.getEmail().equals("employee@test.com")
                        && user.getRole().equals(Role.EMPLOYEE)
        ));
    }


    @Test
    void shouldThrowExceptionWhenEmailExists() {
        User employee = User.builder()
                .email("already@exists.com")
                .identityDocument("99999")
                .phoneNumber("+123456789")
                .build();

        when(persistencePort.getUserByeEmail(employee.getEmail()))
                .thenReturn(User.builder().build());

        assertThrows(EmailAlreadyExistsException.class, () -> useCase.create(employee));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        User employee = User.builder()
                .email("invalid-email")
                .identityDocument("999999")
                .phoneNumber("+573004445566")
                .build();

        when(persistencePort.getUserByeEmail(employee.getEmail()))
                .thenReturn(null);

        assertThrows(InvalidEmailFormatException.class, () -> useCase.create(employee));
    }

    @Test
    void shouldThrowExceptionWhenDocumentIsInvalid() {
        User employee = User.builder()
                .email("employee@test.com")
                .identityDocument("ID123ABC")
                .phoneNumber("+573004445566")
                .build();

        when(persistencePort.getUserByeEmail(employee.getEmail()))
                .thenReturn(null);

        assertThrows(InvalidDocumentException.class, () -> useCase.create(employee));
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
        User employee = User.builder()
                .email("employee@test.com")
                .identityDocument("123456")
                .phoneNumber("ABC123")
                .build();

        when(persistencePort.getUserByeEmail(employee.getEmail()))
                .thenReturn(null);

        assertThrows(InvalidPhoneNumberException.class, () -> useCase.create(employee));
    }
}