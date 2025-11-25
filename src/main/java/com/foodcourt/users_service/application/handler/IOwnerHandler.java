package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;

public interface IOwnerHandler {

    CreateOwnerResponse createOwner(CreateOwnerCommand ownerCommand);

}
