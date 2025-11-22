package com.foodcourt.users_service.domain.usecase;
import com.foodcourt.users_service.domain.model.Client;
import com.foodcourt.users_service.domain.model.Employee;
import com.foodcourt.users_service.domain.port.api.IEmployeeServicePort;

public class CreateEmployeeUseCase implements IEmployeeServicePort {

    @Override
    public boolean createEmployee(Employee employee) {
        return false;
    }
}
