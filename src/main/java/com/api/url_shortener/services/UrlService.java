package com.api.url_shortener.services;

import com.api.url_shortener.dtos.requests.UrlRequest;
import com.api.url_shortener.models.UrlModel;

import java.util.Optional;

public interface UrlService {
    UrlModel save(UrlRequest urlRequest);
    Optional<UrlModel> findById(String id);
    void deleteUrlExpired();
}
