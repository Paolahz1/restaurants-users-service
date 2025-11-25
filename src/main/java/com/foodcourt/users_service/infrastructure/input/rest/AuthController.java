package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.port.IAuthHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario con email y contraseña y devuelve un JWT."
    )

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginCommand request){
        return ResponseEntity.ok(authHandler.login(request));
    }

}


