package com.company.urlshortener.repository;

import com.company.urlshortener.entity.UrlShortenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<UrlShortenerEntity, String> {
}
