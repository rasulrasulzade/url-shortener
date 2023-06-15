package com.company.urlshortener.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ShortUrlDTO {
    private final String urlID;
    private final String url;
    private final LocalDate expDate;
}
