package com.foodcourt.users_service.application.dto.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponse {
    private Long id;
    private String role;
}
