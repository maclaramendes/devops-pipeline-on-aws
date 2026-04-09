package com.ecurtadorlink.url.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecurtadorlink.url.model.Url;
import com.ecurtadorlink.url.repository.UrlRepository;

@Service
public class UrlServices {

    @Autowired
    private UrlRepository urlRepository;

    public Url createUrl(String originalUrl) {
        String shortUrl = generateShortUrl();

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortUrl);
        url.setExpirationDate(LocalDateTime.now().plusDays(30));

        return urlRepository.save(url);
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        Optional<Url> urlOptional = urlRepository.findByShortenedUrl(shortUrl);

        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();

            if (url.getExpirationDate().isAfter(LocalDateTime.now())) {
                return urlOptional;
            } else {
                urlRepository.delete(url);
            }
        }

        return Optional.empty();
    }

    private String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            shortUrl.append(characters.charAt(index));
        }

        return shortUrl.toString();
    }
}