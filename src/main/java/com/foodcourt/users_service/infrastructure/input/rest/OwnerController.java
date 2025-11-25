package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.handler.IOwnerHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/owner/")
@RequiredArgsConstructor
@Tag(name = "Owner", description = "Operaciones para la gestión de propietarios")
public class OwnerController {

    private final IOwnerHandler ownerHandler;

    @Operation(
            summary = "Crear un propietario",
            description = "Permite crear un nuevo propietario (OWNER) en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Propietario creado correctamente"),
            @ApiResponse(responseCode = "409", description = "Ya existe un propietario con la misma información"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, requiere rol OWNER")
    })
    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<CreateOwnerResponse> saveOwner(
            @Parameter(description = "Datos del propietario a crear")
            @RequestBody CreateOwnerCommand ownerCommand
    ) {
        CreateOwnerResponse response = ownerHandler.createOwner(ownerCommand);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
