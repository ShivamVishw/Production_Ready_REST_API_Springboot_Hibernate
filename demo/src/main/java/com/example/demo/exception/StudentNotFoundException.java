package com.example.demo.exception;


// Custom Runtime Exception
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message){
        super(message);
    }
}
