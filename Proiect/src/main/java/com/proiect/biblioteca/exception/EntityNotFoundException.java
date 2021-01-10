package com.proiect.biblioteca.exception;

public class EntityNotFoundException extends RuntimeException{
    private String entityName;

    public EntityNotFoundException(String entityName) {
        super(entityName + " - acest obiect nu a fost gasit");
        this.entityName = entityName;
    }
}
