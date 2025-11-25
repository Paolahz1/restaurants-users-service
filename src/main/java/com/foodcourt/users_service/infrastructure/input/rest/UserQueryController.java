package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.handler.port.IUserInfoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/info/")
@RequiredArgsConstructor
public class UserQueryController {

    private final IUserInfoHandler userInfoHandler;


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
        GetRoleResponse response = userInfoHandler.getRoleById(id);

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
        GetUserByIdResponse response = userInfoHandler.getUserById(id);

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

        GetUserByEmailResponse response = userInfoHandler.getUserByEmail(email);

        if (response == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}


