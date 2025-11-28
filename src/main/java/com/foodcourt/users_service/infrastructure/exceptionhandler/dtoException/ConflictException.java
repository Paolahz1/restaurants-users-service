package com.foodcourt.users_service.infrastructure.exceptionhandler.dtoException;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ConflictException {

    private String message;
    private LocalDateTime createdAt;
    private HttpStatus status;
}
