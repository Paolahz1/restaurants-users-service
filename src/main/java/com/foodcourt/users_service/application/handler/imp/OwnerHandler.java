package com.foodcourt.users_service.application.handler.imp;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.handler.port.IOwnerHandler;
import com.foodcourt.users_service.application.mapper.CreateOwnerMapper;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateOwnerServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional

public class OwnerHandler implements IOwnerHandler {

    private final ICreateOwnerServicePort ownerServicePort;
    private final CreateOwnerMapper ownerCommandMapper;

    @Override
    public CreateOwnerResponse createOwner(CreateOwnerCommand ownerCommand) {
        User owner = ownerCommandMapper.toDomain(ownerCommand);

        ownerServicePort.createOwner(owner);

        return CreateOwnerResponse.builder()
                .success(true)
                .message("Owner created")
                .build();
    }
}
