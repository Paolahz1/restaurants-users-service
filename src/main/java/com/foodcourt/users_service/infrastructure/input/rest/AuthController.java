package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.IAuthHandler;
import com.foodcourt.users_service.application.handler.IOwnerHandler;
import com.foodcourt.users_service.application.handler.IUserInfoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;
    private  final IOwnerHandler ownerHandler;



    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario con email y contraseña y devuelve un JWT."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
    })
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginCommand request){
        return ResponseEntity.ok(authHandler.login(request));
    }


}


