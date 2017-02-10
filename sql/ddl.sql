DROP DATABASE IF EXISTS auktionssystem;
CREATE DATABASE auktionssystem;
USE auktionssystem;

-- adress
CREATE TABLE adress(
id INT AUTO_INCREMENT PRIMARY KEY,
gata VARCHAR (50) NOT NULL,
postnummer CHAR (5) NOT NULL,
ort VARCHAR (50)
);

-- kund
CREATE TABLE kund(
  personnummer INT(10),
  fornamn       VARCHAR(50) NOT NULL,
  efternamn     VARCHAR(50) NOT NULL,
  telefonnummer CHAR(13)    NOT NULL,
  epost         VARCHAR(50) NOT NULL,
  adress_id     INT,
  PRIMARY KEY (personnummer),
  FOREIGN KEY (adress_id) REFERENCES adress (id)
);

-- leverantor
CREATE TABLE leverantor
(
  organisitions_nummer CHAR(12) NOT NULL ,
  name VARCHAR(50),
  telefon_nummer VARCHAR(13),
  epost VARCHAR(50),

  PRIMARY KEY (organisitions_nummer)
);
-- produkt
CREATE TABLE produkt (
  id            INT NOT NULL,
  leverantor_organisationsnummer INT NOT NULL,
  namn          VARCHAR(50),
  provision double NOT NULL ,
  bildnamn VARCHAR(50),
  PRIMARY KEY (id),
  FOREIGN KEY (leverantor_id) REFERENCES leverantor (organisationsnummer)
);


-- auktion

-- bud

-- avslutade auktioner