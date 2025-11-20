package com.foodcourt.users_service.application.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerResponse {

    String response;
    boolean success;
}

