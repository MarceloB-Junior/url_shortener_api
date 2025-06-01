package com.api.url_shortener.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message){
        super(message);
    }
}