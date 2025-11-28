package com.foodcourt.users_service.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Long restaurantId;
    private Role role;
}
