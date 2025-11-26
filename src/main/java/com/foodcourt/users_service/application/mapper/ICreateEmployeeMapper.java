package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeResponse;
import com.foodcourt.users_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICreateEmployeeMapper {

    User toDomain(CreateEmployeeCommand command);

    CreateEmployeeResponse toResponse(User user);
}
