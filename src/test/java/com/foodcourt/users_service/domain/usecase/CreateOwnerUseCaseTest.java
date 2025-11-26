package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.EmailAlreadyExistsException;
import com.foodcourt.users_service.domain.exception.UnderageOwnerException;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOwnerUseCaseTest {

    @Mock
    IUserPersistencePort persistencePort;

    @Mock
    IPasswordEncoderPort encoderPort;

    @Mock
    IUserValidationService validationService;

    @InjectMocks
    CreateOwnerUseCase useCase;

    @Test
    void shouldCreateOwnerSuccessfully() {
        User testOwner = User.builder()
                .firstName("Ana")
                .lastName("Gómez")
                .identityDocument("123456789")
                .phoneNumber("+573005678910")
                .email("ana@mail.com")
                .password("1234")
                .birthDate(LocalDate.of(1990, 5, 10))
                .build();

        when(persistencePort.getUserByeEmail(testOwner.getEmail())).thenReturn(null);
        when(encoderPort.encode(testOwner.getPassword())).thenReturn("encryptedPassword");

        useCase.createOwner(testOwner);

        verify(validationService).validate(testOwner);
        verify(persistencePort).saveUser(argThat(user ->
                user.getEmail().equals("ana@mail.com") &&
                        user.getPassword().equals("encryptedPassword") &&
                        user.getRole() == Role.OWNER
        ));
    }


    @Test
    void shouldThrowExceptionWhenEmailExists() {

        User testOwner = User.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .identityDocument("123456789")
                .phoneNumber("+573005678910")
                .email("test@gmail.com")
                .password("1234")    .birthDate(LocalDate.of(1990, 5, 10))
                .build();

        when(persistencePort.getUserByeEmail(testOwner.getEmail())).thenReturn(User.builder().build());

        assertThrows(EmailAlreadyExistsException.class,
                () -> useCase.createOwner(testOwner));
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsUnderage() {
        User testOwner = User.builder()
                .firstName("Pedro")
                .lastName("López")
                .identityDocument("987654321")
                .phoneNumber("+573001112233")
                .email("pedro@mail.com")
                .password("1234")
                .birthDate(LocalDate.now().minusYears(17))
                .build();

        assertThrows(UnderageOwnerException.class,
                () -> useCase.createOwner(testOwner));
    }

    @Test
    void shouldCallValidationService(){
        User testOwner = User.builder()
                .firstName("Pedro")
                .lastName("López")
                .identityDocument("987654321")
                .phoneNumber("+573001112233")
                .email("pedro@mail.com")
                .password("1234")
                .password("1234")    .birthDate(LocalDate.of(1990, 5, 10))
                .build();

        useCase.createOwner(testOwner);
        verify(validationService).validate(testOwner); //Valida que sí se llamó
    }

}

