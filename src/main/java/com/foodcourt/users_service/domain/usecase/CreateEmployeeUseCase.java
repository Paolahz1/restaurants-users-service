package com.foodcourt.users_service.domain.usecase;
import com.foodcourt.users_service.domain.exception.*;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateEmployeeServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;

public class CreateEmployeeUseCase implements ICreateEmployeeServicePort {

    private final IUserPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;
    private final IUserValidationService validationService;

    public CreateEmployeeUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IUserValidationService validationService) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.validationService = validationService;
    }

    @Override
    public User create(User employee) {

        User user = userPersistencePort.getUserByeEmail(employee.getEmail());
        if(user != null){
            throw new EmailAlreadyExistsException();
        }

        //format of email, phone, identification
        validationService.validate(employee);

        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(passwordEncoderPort.encode(employee.getPassword()));
        return userPersistencePort.saveUser(employee);

    }
}
