package com.api.url_shortener.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlResponse(@JsonProperty(value = "shortened_url") String shortenedUrl) {
}
