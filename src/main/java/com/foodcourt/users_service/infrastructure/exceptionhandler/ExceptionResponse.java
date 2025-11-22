package com.foodcourt.users_service.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    EMAIL_ALREADY_EXISTS("The email provided is already registered"),
    UNDERAGE_OWNER("Owner must be at least 18 years old"),
    INVALID_EMAIL_FORMAT("The email format is invalid"),
    INVALID_DOCUMENT("The identity document must be numeric"),
    INVALID_PHONE_NUMBER("The phone number format is invalid");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
