package com.company.urlshortener.service;

import com.company.urlshortener.dto.CreateShortUrlResponseDTO;
import com.company.urlshortener.dto.ShortUrlDTO;
import org.springframework.web.servlet.view.RedirectView;

public interface UrlShortenerService {

    CreateShortUrlResponseDTO create(ShortUrlDTO dto);

    RedirectView redirect(String urlID);

    void delete(String urlID);
}
