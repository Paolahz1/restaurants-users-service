package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.*;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;

import java.time.LocalDate;
import java.util.logging.Logger;
public class CreateOwnerUseCase implements IOwnerServicePort {

    Logger logger = Logger.getLogger(getClass().getName());
    private  final IOwnerPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;

    public CreateOwnerUseCase(IOwnerPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void createOwner(Owner owner) {

        if(userPersistencePort.existsByEmail(owner.getEmail())){
           logger.info(owner.getEmail());
            throw new EmailAlreadyExistsException(owner.getEmail());
        }

        if (owner.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new UnderageOwnerException();
        }

        // 2. Validar email
        if (!owner.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailFormatException();

        }

        // 3. Validar documento numérico
        if (!owner.getIdentityDocument().matches("\\d+")) {
            throw new InvalidDocumentException();
        }

        // 4. Validar teléfono
        if (!owner.getPhoneNumber().matches("^\\+?\\d{1,13}$")) {
            throw new InvalidPhoneNumberException();
        }

        // 5. Validar mayoría de edad
        if (owner.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new UnderageOwnerException();
        }

        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        userPersistencePort.saveOwner(owner);

    }
}
