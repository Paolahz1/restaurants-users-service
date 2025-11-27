package com.foodcourt.users_service.application.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateOwnerCommand {

    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank private String identityDocument;
    @NotBlank private String phoneNumber;
    @NotBlank private String email;
    @NotNull
    private LocalDate birthDate;
    @NotBlank private String password;

}
