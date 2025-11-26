package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.EmailAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseTest {

    @Mock
    IUserPersistencePort persistencePort;

    @Mock
    IPasswordEncoderPort encoderPort;

    @Mock
    IUserValidationService validationService;

    @InjectMocks
    CreateClientUseCase useCase;


    @Test
    void shouldCreateClientSuccessfully() {
        User testUser = User.builder()
                .firstName("Ana")
                .lastName("Gómez")
                .identityDocument("123456789")
                .phoneNumber("+573005678910")
                .email("ana@mail.com")
                .password("1234")
                .build();

        when(persistencePort.getUserByeEmail(testUser.getEmail())).thenReturn(null);
        when(persistencePort.saveUser(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = useCase.create(testUser);

        assertEquals(Role.CLIENT, result.getRole());
        verify(persistencePort).saveUser(argThat(user ->
                user.getEmail().equals("ana@mail.com")
                        && user.getRole().equals(Role.CLIENT)
        ));
    }


    @Test
    void shouldThrowExceptionWhenEmailExists() {
        User client = User.builder()
                .email("already@exists.com")
                .identityDocument("99999")
                .phoneNumber("+123456789")
                .build();

        when(persistencePort.getUserByeEmail(client.getEmail()))
                .thenReturn(User.builder().build());

        assertThrows(EmailAlreadyExistsException.class, () -> useCase.create(client));
    }
}