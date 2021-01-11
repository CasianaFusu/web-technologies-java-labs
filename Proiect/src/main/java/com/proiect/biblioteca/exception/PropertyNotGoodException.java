package com.proiect.biblioteca.exception;

public class PropertyNotGoodException extends RuntimeException{
    private String propertyName;
    private String whyMessage;

    public PropertyNotGoodException(String propertyName,String whyMessage) {
        super("Proprietatea " + propertyName + " are urmatoarea eroare: " + whyMessage);
        this.propertyName = propertyName;
        this.whyMessage = whyMessage;
    }
}
