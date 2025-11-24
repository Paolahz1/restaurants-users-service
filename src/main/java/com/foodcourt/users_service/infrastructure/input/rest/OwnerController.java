package com.foodcourt.users_service.infrastructure.input.rest;
import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.OwnerResponse;
import com.foodcourt.users_service.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OwnerResponse> saveOwner(@RequestBody CreateOwnerCommand ownerCommand){
        OwnerResponse response = ownerHandler.createOwner(ownerCommand);

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
