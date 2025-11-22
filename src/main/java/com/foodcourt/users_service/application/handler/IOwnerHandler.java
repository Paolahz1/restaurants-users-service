package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.OwnerResponse;

public interface IOwnerHandler {

    OwnerResponse createOwner(CreateOwnerCommand ownerCommand);

}
