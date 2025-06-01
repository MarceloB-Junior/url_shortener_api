package com.api.url_shortener.exceptions;

import com.api.url_shortener.dtos.responses.ExceptionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ExceptionsResponse> handleUrlNotFound(UrlNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionsResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(e.getMessage())
                        .build()
        );
    }
}
