package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.handler.port.IOwnerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/owner")
@RequiredArgsConstructor
@Tag(name = "Owner", description = "Operations related to owner management")
public class OwnerController {

    private final IOwnerHandler ownerHandler;

    @Operation(summary = "Create Owner", description = "Registers a new owner. ADMIN role required.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOwnerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied — ADMIN role required", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email already exists", content = @Content)
    })
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateOwnerResponse> saveOwner(@Valid @RequestBody CreateOwnerCommand ownerCommand) {
        CreateOwnerResponse response = ownerHandler.createOwner(ownerCommand);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
