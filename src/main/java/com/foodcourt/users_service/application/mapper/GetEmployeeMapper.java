package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.get.GetEmployeeResponse;
import com.foodcourt.users_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetEmployeeMapper {
    GetEmployeeResponse toResponse(User user);

}
