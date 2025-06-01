package com.api.url_shortener.services.impl;

import com.api.url_shortener.dtos.requests.UrlRequest;
import com.api.url_shortener.models.UrlModel;
import com.api.url_shortener.repositories.UrlRepository;
import com.api.url_shortener.services.UrlService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public UrlModel save(UrlRequest urlRequest) {
        String shortenedUrl;
        do{
            shortenedUrl = RandomStringUtils.randomAlphanumeric(5,10);
        } while (urlRepository.existsById(shortenedUrl));
        
        var urlModel = new UrlModel();
        urlModel.setShortenedUrl(shortenedUrl);
        urlModel.setLongUrl(urlRequest.url());
        urlModel.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        
        return urlRepository.save(urlModel);
    }

    @Override
    public Optional<UrlModel> findById(String id) {
        return urlRepository.findById(id);
    }

    @Override
    @Scheduled(fixedRate = 15000)
    @Transactional
    public void deleteUrlExpired() {
        urlRepository.deleteUrlExpired();
    }
}
