DROP DATABASE IF EXISTS auktionssystem;
CREATE DATABASE auktionssystem;
USE auktionssystem;

-- adress

-- kund

-- leverantor
CREATE TABLE leverantor
(
  organisitions_nummer CHAR(12) NOT NULL ,
  name VARCHAR(50),
  telefon_nummer VARCHAR(13),
  epost VARCHAR(50),

  PRIMARY KEY (organisitions_nummer)
)
-- produkt

-- auktion

-- bud

-- avslutade auktioner