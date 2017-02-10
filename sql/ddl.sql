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
  id INT AUTO_INCREMENT,
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
CREATE TABLE avslutade_auktioner (
  id INT NOT NULL AUTO_INCREMENT ,
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
INSERT into adress (gata, postnummer, ort) VALUES
  ('Medelstora Torget 1', 10101,'Everthov');
INSERT into adress (gata, postnummer, ort) VALUES
  ('Genvägen 12', 10122,'Tvåskede');
INSERT into adress (gata, postnummer, ort) VALUES
  ('Högbergs gatan 7', 10562,'Lågdalen');
INSERT into adress (gata, postnummer, ort) VALUES
  ('Kvadratvägen 55', 14895,'Plankholm');
INSERT into adress (gata, postnummer, ort) VALUES
  ('Valör gatan 100', 16892,'Njutingö');
INSERT into adress (gata, postnummer, ort) VALUES
  ('Omvägen 69', 19812,'Nedsala');
-- kunder

-- leverantorer
INSERT INTO leverantor VALUES ('111111111111', 'Lovely Old Stuff', '0735111111', 'los@sell.se');
INSERT INTO leverantor VALUES ('222222222222', 'Happy Shop', '0735222222', 'hs@sell.se');
INSERT INTO leverantor VALUES ('333333333333', 'Evil Megastore', '0735333333', 'ems@sell.se');
INSERT INTO leverantor VALUES ('444444444444', 'Friendly Old Dude', '0735444444', 'fod@sell.se');
INSERT INTO leverantor VALUES ('555555555555', 'Ms. Butterscotch', '0735555555', 'msb@sell.se');
INSERT INTO leverantor VALUES ('666666666666', 'We Got The Goods', '0735666666', 'wgtg@sell.se');

-- produkter

-- auktioner
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES (1, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES (2, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES (3, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES (4, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES (5, 3000, 1500, '2017-02-20', '2017-03-20');

-- bud