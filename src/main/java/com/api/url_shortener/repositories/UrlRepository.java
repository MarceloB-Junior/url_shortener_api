package com.api.url_shortener.repositories;

import com.api.url_shortener.models.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel,String> {
    @Modifying
    @Query("DELETE FROM UrlModel u WHERE u.expiresAt < CURRENT_TIMESTAMP")
    void deleteUrlExpired();
}
