package com.foodcourt.users_service.application.dto.get;

import com.foodcourt.users_service.domain.model.Role;

import java.time.LocalDate;

public class GetUserByEmailResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Role role;
}
