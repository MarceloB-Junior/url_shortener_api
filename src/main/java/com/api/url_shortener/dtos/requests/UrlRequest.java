package com.api.url_shortener.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record UrlRequest(@NotBlank String url) {
}
