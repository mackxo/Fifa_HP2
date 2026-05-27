-- Script de base de datos para la API de monedas.
-- Crear primero una base de datos PostgreSQL llamada apimonedas y ejecutar este script alli.

CREATE TABLE IF NOT EXISTS moneda (
    "Id" SERIAL PRIMARY KEY,
    "Moneda" VARCHAR(100),
    "Sigla" VARCHAR(5),
    "Simbolo" VARCHAR(5),
    "Emisor" VARCHAR(100),
    "Imagen" BYTEA
);

CREATE TABLE IF NOT EXISTS cambiomoneda (
    "Id" SERIAL PRIMARY KEY,
    "IdMoneda" INT NOT NULL,
    "Fecha" TIMESTAMP,
    "Cambio" FLOAT,
    CONSTRAINT fk_cambio_moneda FOREIGN KEY ("IdMoneda") REFERENCES moneda("Id")
);

CREATE INDEX IF NOT EXISTS ixCambioMoneda ON cambiomoneda("IdMoneda");

CREATE TABLE IF NOT EXISTS pais (
    "Id" SERIAL PRIMARY KEY,
    "Pais" VARCHAR(50),
    "CodigoAlfa2" VARCHAR(5),
    "CodigoAlfa3" VARCHAR(5),
    "IdMoneda" INT,
    "Mapa" BYTEA,
    "Bandera" BYTEA,
    CONSTRAINT fk_pais_moneda FOREIGN KEY ("IdMoneda") REFERENCES moneda("Id")
);

CREATE INDEX IF NOT EXISTS ixPais ON pais("Pais");

INSERT INTO moneda ("Moneda", "Sigla", "Simbolo", "Emisor")
VALUES
    ('Peso colombiano', 'COP', '$', 'Banco de la Republica'),
    ('Dolar estadounidense', 'USD', '$', 'Federal Reserve'),
    ('Euro', 'EUR', 'EUR', 'Banco Central Europeo'),
    ('Peso mexicano', 'MXN', '$', 'Banco de Mexico')
ON CONFLICT DO NOTHING;

INSERT INTO pais ("Pais", "CodigoAlfa2", "CodigoAlfa3", "IdMoneda")
VALUES
    ('Colombia', 'CO', 'COL', 1),
    ('Estados Unidos', 'US', 'USA', 2),
    ('Alemania', 'DE', 'DEU', 3),
    ('Mexico', 'MX', 'MEX', 4)
ON CONFLICT DO NOTHING;

INSERT INTO cambiomoneda ("IdMoneda", "Fecha", "Cambio")
VALUES
    (1, '2024-01-01 00:00:00', 3926.83),
    (1, '2024-02-01 00:00:00', 3980.50),
    (1, '2024-03-01 00:00:00', 4010.25),
    (2, '2024-01-01 00:00:00', 1.0),
    (3, '2024-01-01 00:00:00', 1.08)
ON CONFLICT DO NOTHING;
