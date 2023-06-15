package com.company.urlshortener.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "url_short")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlShortenerEntity {
    @Id
    @Column(name = "url_id")
    String urlID;

    String shortUrl;

    String longUrl;

    @Column(name = "exp_date")
    LocalDate expDate;
}
