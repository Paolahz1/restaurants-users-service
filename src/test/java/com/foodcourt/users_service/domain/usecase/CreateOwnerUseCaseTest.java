    package com.foodcourt.users_service.domain.usecase;

    import com.foodcourt.users_service.domain.exception.EmailAlreadyExistsException;
    import com.foodcourt.users_service.domain.exception.InvalidEmailFormatException;
    import com.foodcourt.users_service.domain.exception.InvalidPhoneNumberException;
    import com.foodcourt.users_service.domain.exception.UnderageOwnerException;
    import com.foodcourt.users_service.domain.model.Owner;
    import com.foodcourt.users_service.domain.model.Role;
    import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;
    import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
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
        IOwnerPersistencePort persistencePort;

        @Mock
        IPasswordEncoderPort encoderPort;

        @InjectMocks
        CreateOwnerUseCase useCase;


        @Test
        void shouldCreateOwnerSuccessfully(){
            Owner testOwner = Owner.builder()
                    .firstName("Ana")
                    .lastName("Gómez")
                    .identityDocument("123456789")
                    .phoneNumber("+573005678910")
                    .email("ana@mail.com")
                    .password("1234")
                    .birthDate(LocalDate.of(1990, 5, 10))
                    .role(Role.OWNER)
                    .build();

            when(persistencePort.existsByEmail(testOwner.getEmail())).thenReturn(false);
            when(encoderPort.encode(testOwner.getPassword())).thenReturn("encryptedPassword");

            useCase.createOwner(testOwner);

            // verificamos que se guardó el owner
            verify(persistencePort).saveOwner(argThat(owner ->
                    owner.getEmail().equals("ana@mail.com") &&
                            owner.getPassword().equals("encryptedPassword") &&
                            owner.getRole() == Role.OWNER
            ));
        }

        @Test
        void shouldThrowExceptionWhenEmailExists() {

            Owner testOwner = Owner.builder()
                    .firstName("Juan")
                    .lastName("Pérez")
                    .identityDocument("123456789")
                    .phoneNumber("+573005678910")
                    .email("test@gmail.com")
                    .password("1234")
                    .birthDate(LocalDate.of(1990, 5, 10))
                    .build();

            when(persistencePort.existsByEmail(testOwner.getEmail())).thenReturn(true);

            assertThrows(EmailAlreadyExistsException.class,
                    () -> useCase.createOwner(testOwner));
        }

        @Test
        void shouldThrowExceptionWhenOwnerIsUnderage() {
            Owner testOwner = Owner.builder()
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
        void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
            Owner invalidPhoneOwner = Owner.builder()
                    .firstName("Carlos")
                    .lastName("Ruiz")
                    .identityDocument("123456789")
                    .phoneNumber("12345678901234")
                    .email("carlos@mail.com")
                    .password("1234")
                    .birthDate(LocalDate.of(1990, 5, 10))
                    .build();

            assertThrows(InvalidPhoneNumberException.class,
                    () -> useCase.createOwner(invalidPhoneOwner));
        }

        @Test
        void shouldThrowExceptionWhenEmailIsInvalid() {
            Owner invalidEmailOwner = Owner.builder()
                    .firstName("Laura")
                    .lastName("Martínez")
                    .identityDocument("12345678")
                    .phoneNumber("+573005678910")
                    .email("correo-invalido")
                    .password("1234")
                    .birthDate(LocalDate.of(1990, 5, 10))
                    .build();

            when(persistencePort.existsByEmail(invalidEmailOwner.getEmail())).thenReturn(false);

            assertThrows(InvalidEmailFormatException.class,
                    () -> useCase.createOwner(invalidEmailOwner));
        }

    }

