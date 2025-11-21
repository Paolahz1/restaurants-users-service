package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.OwnerResponse;
import com.foodcourt.users_service.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners/")
@RequiredArgsConstructor
public class UsersController {

    private  final IOwnerHandler ownerHandler;

    @PostMapping
    public ResponseEntity<OwnerResponse> saveOwner(@RequestBody CreateOwnerCommand ownerCommand){
        OwnerResponse response = ownerHandler.createOwner(ownerCommand);

        if(!response.isSuccess()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
