package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.Client;
import com.foodcourt.users_service.domain.model.Employee;

public interface IClientServicePort
{
    boolean createEmployee(Client client);
}
