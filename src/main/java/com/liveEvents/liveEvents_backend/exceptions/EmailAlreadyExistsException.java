package com.liveEvents.liveEvents_backend.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
        super("Cet email est déjà utilisé.");
    }
}