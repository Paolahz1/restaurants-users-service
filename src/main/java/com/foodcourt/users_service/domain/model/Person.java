package com.foodcourt.users_service.domain.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public abstract class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private String password; // bcrypt

}
