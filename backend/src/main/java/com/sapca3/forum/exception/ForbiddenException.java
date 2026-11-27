package com.sapca3.forum.exception;


public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}