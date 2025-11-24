package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.GetRoleResponse;
import com.foodcourt.users_service.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetRoleResponseMapper {

    @Mapping(target = "role", expression = "java(role.name())")
    GetRoleResponse toResponse(Role role);}
