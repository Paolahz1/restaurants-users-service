package com.foodcourt.users_service.application.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeCommand {

    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank private String identityDocument;
    @NotBlank private String phoneNumber;
    @NotBlank private String email;
    @NotBlank private String password;
    @NotBlank private Long restaurantId;

}
