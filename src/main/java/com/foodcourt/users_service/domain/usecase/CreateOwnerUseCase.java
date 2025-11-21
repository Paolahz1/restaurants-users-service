package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.EmailAlreadyExistsException;
import com.foodcourt.users_service.domain.exception.UnderageOwnerException;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;

import java.time.LocalDate;


public class CreateOwnerUseCase implements IOwnerServicePort {

    private  final IOwnerPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;

    public CreateOwnerUseCase(IOwnerPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void createOwner(Owner owner) {

        if(userPersistencePort.existsByEmail(owner.getEmail())){
            throw new EmailAlreadyExistsException(owner.getEmail());
        }

        if (owner.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new UnderageOwnerException();
        }

        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        userPersistencePort.saveOwner(owner);

    }
}
