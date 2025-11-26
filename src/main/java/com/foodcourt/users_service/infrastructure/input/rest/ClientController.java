package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.create.CreateClientCommand;
import com.foodcourt.users_service.application.dto.create.CreateClientResponse;
import com.foodcourt.users_service.application.handler.port.IClientHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-service/client/")
@RequiredArgsConstructor
@Tag(name = "Client", description = "")
public class ClientController {

    private final IClientHandler clientHandler;

    @PostMapping
    public ResponseEntity<CreateClientResponse> saveClient(@RequestBody CreateClientCommand command) {
        CreateClientResponse response = clientHandler.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
