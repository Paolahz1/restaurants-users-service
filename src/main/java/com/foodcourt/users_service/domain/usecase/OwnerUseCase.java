package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;


public class OwnerUseCase implements IOwnerServicePort {

    private  final IUserPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;

    public OwnerUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public boolean createOwner(Owner owner) {


        return false;
    }
}
