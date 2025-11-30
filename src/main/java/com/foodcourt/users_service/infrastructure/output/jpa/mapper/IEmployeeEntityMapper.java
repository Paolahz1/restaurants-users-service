package com.foodcourt.users_service.infrastructure.output.jpa.mapper;

import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.EmployeeDetailsEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeEntityMapper {

    User toDomain(EmployeeDetailsEntity entity);

}
