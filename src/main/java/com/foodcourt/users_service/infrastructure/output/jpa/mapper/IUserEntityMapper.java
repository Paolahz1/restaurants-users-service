package com.foodcourt.users_service.infrastructure.output.jpa.mapper;

import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity entity);

}
