package com.foodcourt.users_service.domain.usecase;
import com.foodcourt.users_service.domain.exception.*;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.IEmployeeServicePort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;

public class CreateEmployeeUseCase implements IEmployeeServicePort {

    private final IUserPersistencePort authPersistencePort;

    public CreateEmployeeUseCase(IUserPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public void createEmployee(User employee) {

        User user = authPersistencePort.getUserByeEmail(employee.getEmail());
        if(user != null){
            throw new EmailAlreadyExistsException();
        }

        if (!employee.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailFormatException();
        }

        if (!employee.getIdentityDocument().matches("\\d+")) {
            throw new InvalidDocumentException();
        }

        if (!employee.getPhoneNumber().matches("^\\+?\\d{1,13}$")) {
            throw new InvalidPhoneNumberException();
        }

        employee.setRole(Role.EMPLOYEE);
        authPersistencePort.saveUser(employee);
    }
}
