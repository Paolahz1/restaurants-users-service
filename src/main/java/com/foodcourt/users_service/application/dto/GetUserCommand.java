package com.foodcourt.users_service.application.dto;

import com.foodcourt.users_service.domain.model.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GetUserCommand {

    private Long id;

}
