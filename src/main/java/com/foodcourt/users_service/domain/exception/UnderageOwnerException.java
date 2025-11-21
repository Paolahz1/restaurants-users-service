package com.foodcourt.users_service.domain.exception;

public class UnderageOwnerException extends  RuntimeException{

    public UnderageOwnerException( ) {
        super("The owner must be at least 18 years old ");
    }

}
