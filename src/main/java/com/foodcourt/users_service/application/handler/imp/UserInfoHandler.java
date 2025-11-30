package com.foodcourt.users_service.application.handler.imp;

import com.foodcourt.users_service.application.dto.get.GetEmployeeResponse;
import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.handler.port.IUserInfoHandler;
import com.foodcourt.users_service.application.mapper.GetEmployeeMapper;
import com.foodcourt.users_service.application.mapper.GetRoleMapper;
import com.foodcourt.users_service.application.mapper.GetUserByEmailMapper;
import com.foodcourt.users_service.application.mapper.GetUserByIdlMapper;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.IUserGetInfoServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional

public class UserInfoHandler implements IUserInfoHandler {

    private final IUserGetInfoServicePort userGetInfoServicePort;

    private final GetRoleMapper getRolResponseMapper;
    private final GetUserByEmailMapper getUserByEmailResponseMapper;
    private final GetUserByIdlMapper getUserResponseMapper;
    private final GetEmployeeMapper employeeMapper;

    @Override
    public GetRoleResponse getRoleById(Long userId) {
        Role role = userGetInfoServicePort.getUserRoleById(userId);
        return getRolResponseMapper.toResponse(role);
    }

    @Override
    public GetUserByIdResponse getUserById(Long userId) {
        User user = userGetInfoServicePort.getUserById(userId);
        return getUserResponseMapper.toResponse(user);
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(String email) {
        User user = userGetInfoServicePort.getUserByEmail(email);
        return getUserByEmailResponseMapper.toResponse(user);
    }

    @Override
    public GetEmployeeResponse getEmployeeById(long employeeId) {
       User user = userGetInfoServicePort.getEmployeeById(employeeId);
        return employeeMapper.toResponse(user);
    }


}


