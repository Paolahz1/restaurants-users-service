package com.foodcourt.users_service.application.handler.port;

import com.foodcourt.users_service.application.dto.create.CreateClientCommand;
import com.foodcourt.users_service.application.dto.create.CreateClientResponse;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;

public interface IClientHandler {

    CreateClientResponse create(CreateClientCommand command);

}
