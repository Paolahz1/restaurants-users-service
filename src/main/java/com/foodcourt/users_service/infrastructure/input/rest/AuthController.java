package com.foodcourt.users_service.infrastructure.input.rest;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.IAuthHandler;

import com.foodcourt.users_service.application.handler.IOwnerHandler;
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




    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginCommand request){
        return ResponseEntity.ok(authHandler.login(request));
    }

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


