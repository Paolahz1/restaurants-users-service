package com.foodcourt.users_service.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class GetUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private String role;

}
