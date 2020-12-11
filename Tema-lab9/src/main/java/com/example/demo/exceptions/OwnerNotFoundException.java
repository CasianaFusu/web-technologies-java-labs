package com.example.demo.exceptions;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(String message) {
        super(message);
    }
}
