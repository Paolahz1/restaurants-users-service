package com.foodcourt.users_service.application.dto.get;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRoleResponse {

    private String role;
}
