package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.OwnerResponse;
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
    public OwnerResponse createOwner(CreateOwnerCommand ownerCommand) {
        Owner owner = ownerCommandMapper.toOwner(ownerCommand);

        ownerServicePort.createOwner(owner);

        return OwnerResponse.builder()
                .success(true)
                .message("Owner created")
                .build();
    }
}
