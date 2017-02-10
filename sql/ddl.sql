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
  organisitionsnummer CHAR(12) NOT NULL ,
  name VARCHAR(50),
  telefonnummer VARCHAR(13),
  epost VARCHAR(50),

  PRIMARY KEY (organisitionsnummer)
);
-- produkt
CREATE TABLE produkt (
  id            INT NOT NULL,
  leverantor_organisationsnummer INT NOT NULL,
  namn          VARCHAR(50),
  provision double NOT NULL ,
  bildnamn VARCHAR(50),
  PRIMARY KEY (id),
  FOREIGN KEY (leverantor_organisationsnummer) REFERENCES leverantor (organisitionsnummer)
);

-- auktion
CREATE TABLE auktion (
  id          INT AUTO_INCREMENT,
  produkt_id  INT,
  acceptpris  DOUBLE,
  utgangspris DOUBLE NOT NULL,
  startdatum  DATE   NOT NULL,
  slutdatum   DATE   NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (produkt_id) REFERENCES produkt (id)
);
-- bud

CREATE TABLE bud (
  kund_personnummer INT NOT NULL,
  auktion_id INT NOT NULL,
  PRIMARY KEY (kund_personnummer, auktion_id),
  FOREIGN KEY (kund_personnummer) REFERENCES kund (personnummer),
  FOREIGN KEY (auktion_id) REFERENCES auktion (id)
);

-- avslutade auktioner
CREATE TABLE avslutade_auktioner
(
  id INT NOT NULL ,
  produkt_id INT,
  hogsta_bud DOUBLE,
  kund_personnummer CHAR(10),
  startdatum DATE,
  slutdatum DATE,
  utgangspris DOUBLE,
  acceptpris DOUBLE,

  PRIMARY KEY (id),
  FOREIGN KEY (produkt_id) REFERENCES produkt (produkt_id)
);


-- Tabeller klara

# Insert DATA time

-- addresser

-- kunder
INSERT INTO kund
(personnummer, fornamn, efternamn, telefonnummer, epost, adress_id) VALUES
  (6808033117, "Fritte", "Bohman", "07374826", "frittw.bohman@domäinen.de", 1),
  (3212077743, "Anna", "Lund", "0743782644", "hacker.c8s@anon.w", 2),
  (8707736734, "Noppe", "Segelbåt", "0798375892", "noppe.segelbåt@buissenes.com", 3),
  (7309824728, "Limpan", "Persson", "0734683844", "limpan123.ha@hotmail.com", 4),
  (5503047294, "Edit", "Gärdeström", "0794782828", "vadsadu@virus.com", 5),
  (7706034568, "Bella", "Bortskämd", "0783672837", "Bellam@bloggen.se", 6);


-- leverantorer

-- produkter

-- auktioner

-- bud