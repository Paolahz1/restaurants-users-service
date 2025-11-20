package com.foodcourt.users_service.domain.usecase;
import com.foodcourt.users_service.domain.model.Client;
import com.foodcourt.users_service.domain.port.api.IClientServicePort;

public class EmployeeUseCase implements IClientServicePort {


    @Override
    public boolean createEmployee(Client client) {
        return false;
    }
}
