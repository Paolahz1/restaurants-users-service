package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.IUserGetInfoServicePort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;

public class GetUserInfoUseCase implements IUserGetInfoServicePort {

    private final IUserPersistencePort userPersistencePort;

    public GetUserInfoUseCase(IUserPersistencePort authPersistencePort) {
        this.userPersistencePort = authPersistencePort;
    }

    public Role getUserRoleById(Long userId) {
        return  userPersistencePort.getRoleById(userId);
    }

    public User getUserById(Long userId) {
        return userPersistencePort.getUserById(userId);
    }


    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByeEmail(email);
    }

    @Override
    public User getEmployeeById(long employeeId) {
        return userPersistencePort.getEmployeeById(employeeId);
    }

}

