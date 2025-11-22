package com.foodcourt.users_service.domain.model;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Client extends Person{

    private  Role role = Role.CLIENT;

}
