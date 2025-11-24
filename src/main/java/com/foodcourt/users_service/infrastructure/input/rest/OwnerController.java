package com.foodcourt.users_service.infrastructure.input.rest;
import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-service/owner/")
@RequiredArgsConstructor
public class OwnerController {

    private  final IOwnerHandler ownerHandler;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<CreateOwnerResponse> saveOwner(@RequestBody CreateOwnerCommand ownerCommand){
        CreateOwnerResponse response = ownerHandler.createOwner(ownerCommand);

        if(!response.isSuccess()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(response);
        }

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(response);

    }
}
