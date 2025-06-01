package com.api.url_shortener.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TB_URLS")
public class UrlModel implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    private String shortenedUrl;
    @Column(columnDefinition = "TEXT")
    private String longUrl;
    private LocalDateTime expiresAt;
}
