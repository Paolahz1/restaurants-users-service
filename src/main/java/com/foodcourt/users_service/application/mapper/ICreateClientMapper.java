package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.create.CreateClientCommand;
import com.foodcourt.users_service.application.dto.create.CreateClientResponse;
import com.foodcourt.users_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICreateClientMapper {

    User toDomain(CreateClientCommand command);

    CreateClientResponse toResponse(User user);
}
