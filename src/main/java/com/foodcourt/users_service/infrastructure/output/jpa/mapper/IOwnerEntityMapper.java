package com.foodcourt.users_service.infrastructure.output.jpa.mapper;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOwnerEntityMapper {

    @Mapping(target = "role", constant = "OWNER")
    UserEntity toEntity(Owner owner);

    Owner toDomain(UserEntity entity);

}
