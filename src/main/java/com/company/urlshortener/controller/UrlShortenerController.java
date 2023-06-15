package com.company.urlshortener.controller;

import com.company.urlshortener.dto.CreateShortUrlResponseDTO;
import com.company.urlshortener.dto.ShortUrlDTO;
import com.company.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService service;

    @PostMapping
    CreateShortUrlResponseDTO generate(@RequestBody ShortUrlDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    RedirectView redirectToUrl(@PathVariable String id) {
        return service.redirect(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
