package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.Employee;

public interface IEmployeeServicePort
{
    boolean createEmployee(Employee employee);
}
