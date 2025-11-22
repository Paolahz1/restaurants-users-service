package com.foodcourt.users_service.domain.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public class Owner extends Person{

    private LocalDate birthDate;
    private Role role = Role.OWNER;
}
