package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeResponse;
import com.foodcourt.users_service.application.handler.port.IEmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-service/employee")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Employee management operations")
public class EmployeeController {

    private final IEmployeeHandler handler;

    @Operation(summary = "Create Employee", description = "Registers a new employee. OWNER role required.")
    @PostMapping("/")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeCommand command){
        CreateEmployeeResponse response = handler.create(command);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
