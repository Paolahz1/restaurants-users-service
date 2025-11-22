package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.domain.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateOwnerCommandMapper {

    @Mapping(target = "role", ignore = true)
    Owner toOwner(CreateOwnerCommand ownerCommand);

}
