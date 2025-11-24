package com.foodcourt.users_service.application.dto;

import lombok.Data;

@Data
public class UpdateDishCommand {
    private Long price;       // opcional
    private String description; // opcional
}

