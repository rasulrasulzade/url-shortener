package com.company.urlshortener.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortUrlResponseDTO {
    private String shortUrl;
    private String urlID;
}
