package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.IAuthHandler;

import com.foodcourt.users_service.application.handler.IOwnerHandler;
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

    @Operation(
            summary = "Obtener rol del usuario",
            description = "Devuelve el rol asociado al ID del usuario."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("role/{id}")
    public ResponseEntity<GetRoleResponse> getRoleById(@PathVariable Long id){
        GetRoleResponse response = authHandler.getRoleById(id);

        if (response == null || response.getRole() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);

    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve la información del usuario asociado al ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/byId/{id}")
    public ResponseEntity<GetUserByIdResponse> getUserById(@PathVariable Long id){
        GetUserByIdResponse response = authHandler.getUserById(id);

        if (response == null || response.getRole() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);

    }

    @Operation(
            summary = "Obtener usuario por email",
            description = "Devuelve la información del usuario asociado al email."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("email/{email}")
    public ResponseEntity<GetUserByEmailResponse> getUserByEmail(@PathVariable String email){

        GetUserByEmailResponse response = authHandler.getUserByEmail(email);

        if (response == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}


