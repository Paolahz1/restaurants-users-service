package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.domain.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CreateOwnerMapper {

    User toDomain(CreateOwnerCommand ownerCommand);

}
