package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.mapper.CreateOwnerCommandMapper;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional

public class OwnerHandler implements IOwnerHandler {

    private final IOwnerServicePort ownerServicePort;
    private final CreateOwnerCommandMapper ownerCommandMapper;

    @Override
    public CreateOwnerResponse createOwner(CreateOwnerCommand ownerCommand) {
        Owner owner = ownerCommandMapper.toOwner(ownerCommand);

        ownerServicePort.createOwner(owner);

        return CreateOwnerResponse.builder()
                .success(true)
                .message("Owner created")
                .build();
    }
}
