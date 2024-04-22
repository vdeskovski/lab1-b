package com.example.lab1b.model.exceptions;

public class CountryAlreadyExistsException extends RuntimeException{
    public CountryAlreadyExistsException(String message) {
        super(message);
    }
}
