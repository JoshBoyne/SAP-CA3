package com.sapca3.forum.exception;

public class UnauthorisedException extends RuntimeException{
    public UnauthorisedException(String message){
        super(message);
    }
}
