package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.handler.port.IUserInfoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-service/users/info")
@RequiredArgsConstructor
@Tag(name = "User Info", description = "Endpoints for querying user information")
public class UserQueryController {

    private final IUserInfoHandler userInfoHandler;


    @Operation(summary = "Get user role by ID", description = "Returns the role of a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("role/{id}")
    public ResponseEntity<GetRoleResponse> getRoleById(@Valid @PathVariable Long id){
        GetRoleResponse response = userInfoHandler.getRoleById(id);

        if (response == null || response.getRole() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);

    }

    @Operation(summary = "Get user by ID", description = "Returns the details of a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
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

    @Operation(summary = "Get user by email", description = "Returns the details of a user by their email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/email/{email}")
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


