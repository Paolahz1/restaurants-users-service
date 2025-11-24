package com.foodcourt.users_service.application.dto.get;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class GetUserByIdResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private String role;

}
