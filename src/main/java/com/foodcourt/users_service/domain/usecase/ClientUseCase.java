package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.model.Client;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.port.api.IClientServicePort;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;


public class ClientUseCase implements IClientServicePort {


    @Override
    public boolean createEmployee(Client client) {
        return false;
    }
}
