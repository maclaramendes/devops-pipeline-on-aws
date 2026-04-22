package com.ecurtadorlink.url.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecurtadorlink.url.model.Url;
import com.ecurtadorlink.url.services.UrlServices;

@RestController
@RequestMapping
public class Urlcontroller {

    @Autowired
    private UrlServices urlServices;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API running");
    }

    @PostMapping("/shorten")
public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
    String originalUrl = request.get("originalUrl");

    Url url = urlServices.createUrl(originalUrl);

    Map<String, String> response = new HashMap<>();
    response.put("originalUrl", url.getOriginalUrl());
    response.put("shortUrl", "/" + url.getShortenedUrl());

    return ResponseEntity.ok(response);
}

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String shortUrl) {
        Optional<Url> urlOptional = urlServices.getOriginalUrl(shortUrl);

        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            return ResponseEntity.status(302)
                    .location(URI.create(url.getOriginalUrl()))
                    .build();
        }

        return ResponseEntity.notFound().build();
    }
}