package com.foodcourt.users_service.application.handler.imp;

import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeResponse;
import com.foodcourt.users_service.application.handler.port.IEmployeeHandler;
import com.foodcourt.users_service.application.mapper.ICreateEmployeeMapper;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateEmployeeServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeHandler implements IEmployeeHandler {

    private final ICreateEmployeeServicePort createEmployeeServicePort;
    private final ICreateEmployeeMapper createEmployeeMapper;

    @Override
    public CreateEmployeeResponse create(CreateEmployeeCommand command) {

        User user = createEmployeeMapper.toDomain(command);
        User userCreated = createEmployeeServicePort.create(user);

        return createEmployeeMapper.toResponse(userCreated);

    }
}


