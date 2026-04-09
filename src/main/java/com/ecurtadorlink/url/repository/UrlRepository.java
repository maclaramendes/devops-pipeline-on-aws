package com.ecurtadorlink.url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecurtadorlink.url.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortenedUrl(String shortenedUrl);
}