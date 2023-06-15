DROP SCHEMA IF EXISTS "url_shortener" CASCADE;

CREATE SCHEMA "url_shortener";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS url_shortener.url_short CASCADE;

CREATE TABLE url_shortener.url_short
(
    url_id    character varying PRIMARY KEY,
    short_url character varying NOT NULL,
    long_url  character varying NOT NULL,
    exp_date  DATE
);