package com.foodcourt.users_service.application.mapper;

import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.domain.model.Person;
import com.foodcourt.users_service.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class GetUserResponseMapper {

    public GetUserByIdResponse toResponse(Person person, Role role) {
        if (person == null) return null;

        return GetUserByIdResponse.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .identityDocument(person.getIdentityDocument())
                .birthDate(person instanceof com.foodcourt.users_service.domain.model.Owner owner
                        ? owner.getBirthDate()
                        : null)
                .role(role != null ? role.name() : null)
                .build();
    }
}
