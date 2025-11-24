package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.GetRoleResponse;
import com.foodcourt.users_service.application.handler.IAuthHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-service/users/")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;

    @GetMapping("{id}/role")
    public ResponseEntity<GetRoleResponse> getRoleById(@PathVariable Long id){
        GetRoleResponse response = authHandler.getRoleById(id);

        if (response == null || response.getRole() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);

    }

}



