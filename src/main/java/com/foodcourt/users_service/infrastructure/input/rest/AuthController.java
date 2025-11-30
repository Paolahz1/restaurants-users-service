package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.port.IAuthHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user login and JWT authentication")
public class AuthController {

    private final IAuthHandler authHandler;

    @Operation(summary = "User login", description = "Authenticate user and return JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginCommand request){

                System.out.print(request.getPassword() + " " + request.getEmail());
        String token = String.valueOf(authHandler.login(request));
        return ResponseEntity.ok(authHandler.login(request));
    }

}


