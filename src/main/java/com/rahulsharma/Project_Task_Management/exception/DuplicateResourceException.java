package com.rahulsharma.Project_Task_Management.exception;

import org.springframework.http.ResponseEntity;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message){
        super(message);
    }
}
