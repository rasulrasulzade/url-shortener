package com.company.urlshortener.service;

import com.company.urlshortener.dto.CreateShortUrlResponseDTO;
import com.company.urlshortener.dto.ShortUrlDTO;
import com.company.urlshortener.entity.UrlShortenerEntity;
import com.company.urlshortener.exception.CommonException;
import com.company.urlshortener.repository.UrlShortenerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final UrlShortenerRepository repository;

    @Value("${baseUrl}")
    private String baseUrl;

    @Override
    public CreateShortUrlResponseDTO create(ShortUrlDTO dto) {
        String urlID = null;
        if (dto.getUrlID() != null) {
            boolean exists = repository.existsById(dto.getUrlID());
            if (exists) {
                log.info("Short url with id: {} is already used", dto.getUrlID());
                throw new CommonException("Short url with id: " + dto.getUrlID() + " is already used!", HttpStatus.BAD_REQUEST);
            }

            urlID = dto.getUrlID();
        } else {
            urlID = generateUniqueAlphanumericID();
        }

        UrlShortenerEntity entity = UrlShortenerEntity.builder()
                .longUrl(dto.getUrl())
                .shortUrl(baseUrl + "/" + urlID)
                .urlID(urlID)
                .expDate(dto.getExpDate())
                .build();
        log.info("Url short: {} is saving", entity);
        repository.save(entity);
        log.info("Url short with id: {} is saved", entity.getUrlID());

        return CreateShortUrlResponseDTO.builder()
                .shortUrl(entity.getShortUrl())
                .urlID(urlID)
                .build();
    }

    @Override
    public RedirectView redirect(String urlID) {
        UrlShortenerEntity entity = repository.findById(urlID).orElseThrow(() -> {
            log.info("Short url with id: {} not found", urlID);
            return new CommonException("Short url with id: " + urlID + " not found", HttpStatus.NOT_FOUND);
        });

        if (entity.getExpDate() != null && entity.getExpDate().isBefore(LocalDate.now())) {
            log.info("Short url with id: {} is expired", urlID);
            throw new CommonException("Short url with id: " + urlID + " is expired", HttpStatus.BAD_REQUEST);
        }

        log.info("Redirecting to: {}", entity.getLongUrl());
        return new RedirectView(entity.getLongUrl());
    }

    @Override
    public void delete(String urlID) {
        repository.deleteById(urlID);
        log.info("Url sort with id: {} is deleted", urlID);
    }

    private String generateUniqueAlphanumericID() {
        String str = String.valueOf(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (char i : str.toCharArray()) {
            char ch = (char) (97 + Character.getNumericValue(i));
            sb.append(ch);
        }
        return sb.toString();
    }
}
