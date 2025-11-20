package com.foodcourt.users_service.domain.model;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Admin extends Person{

    private final Role role = Role.ADMIN;

}
