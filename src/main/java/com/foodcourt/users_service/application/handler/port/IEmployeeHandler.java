package com.foodcourt.users_service.application.handler.port;

import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeResponse;

public interface IEmployeeHandler {

    CreateEmployeeResponse create(CreateEmployeeCommand command);

}
