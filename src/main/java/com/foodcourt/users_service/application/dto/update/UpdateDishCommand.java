package com.foodcourt.users_service.application.dto.update;

import lombok.Data;

@Data
public class UpdateDishCommand {
    private Long price;
    private String description;
}

