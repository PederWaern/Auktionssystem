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

-- produkt

-- auktion

-- bud

CREATE TABLE bud (
  kund_id INT NOT NULL,
  auktion_id INT NOT NULL,
  PRIMARY KEY (kund_id, auktion_id),
  FOREIGN KEY (kund_id) REFERENCES kund (id),
  FOREIGN KEY (auktion_id) REFERENCES auktion (id)
);

-- avslutade auktioner