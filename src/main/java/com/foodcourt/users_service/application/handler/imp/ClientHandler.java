package com.foodcourt.users_service.application.handler.imp;

import com.foodcourt.users_service.application.dto.create.CreateClientCommand;
import com.foodcourt.users_service.application.dto.create.CreateClientResponse;
import com.foodcourt.users_service.application.handler.port.IClientHandler;
import com.foodcourt.users_service.application.mapper.ICreateClientMapper;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateClientServicePort;
import com.foodcourt.users_service.domain.port.api.ICreateEmployeeServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class ClientHandler implements IClientHandler {

    private final ICreateClientServicePort createClientServicePort;
    private final ICreateClientMapper mapper;

    @Override
    public CreateClientResponse create(CreateClientCommand command) {
        User user = mapper.toDomain(command);
        User userCreated = createClientServicePort.create(user);
        return mapper.toResponse(userCreated);
    }
}


