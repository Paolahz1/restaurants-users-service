package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetRoleMapper {

    GetRoleResponse toResponse(Role role);}
