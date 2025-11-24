package com.foodcourt.users_service.application.dto.create;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOwnerResponse {
    String message;
    boolean success;
}

