package com.foodcourt.users_service.application.dto.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClientCommand {

    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private String password;

}
