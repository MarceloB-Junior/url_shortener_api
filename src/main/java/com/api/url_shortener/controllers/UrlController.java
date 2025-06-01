package com.api.url_shortener.controllers;

import com.api.url_shortener.dtos.requests.UrlRequest;
import com.api.url_shortener.dtos.responses.UrlResponse;
import com.api.url_shortener.exceptions.UrlNotFoundException;
import com.api.url_shortener.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody @Valid UrlRequest urlRequest, HttpServletRequest request){
        var savedUrl = urlService.save(urlRequest);
        var redirectUrl = request.getRequestURL().toString().replace("shorten-url", savedUrl.getShortenedUrl());
        return ResponseEntity.ok(new UrlResponse(redirectUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectUrl(@PathVariable(value = "id") String id){
        var url = urlService.findById(id)
                .orElseThrow(() -> new UrlNotFoundException("Url Not Found."));
        var headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getLongUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
