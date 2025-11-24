package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.*;
import com.foodcourt.users_service.application.handler.IAuthHandler;

import com.foodcourt.users_service.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;
    private  final IOwnerHandler ownerHandler;


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

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginCommand request){
        return ResponseEntity.ok(authHandler.login(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Long id){
        GetUserResponse response = authHandler.getUser(id);

        if (response == null || response.getRole() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);

    }



}


