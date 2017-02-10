DROP DATABASE IF EXISTS auktionssystem;
CREATE DATABASE auktionssystem;
USE auktionssystem;

-- adress
CREATE TABLE adress (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  gata       VARCHAR(50) NOT NULL,
  postnummer CHAR(5)     NOT NULL,
  ort        VARCHAR(50)
);

-- kund
CREATE TABLE kund (
  personnummer  CHAR(10),
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
  organisitionsnummer CHAR(12) NOT NULL,
  name                VARCHAR(50),
  telefonnummer       VARCHAR(13),
  epost               VARCHAR(50),

  PRIMARY KEY (organisitionsnummer)
);
-- produkt
CREATE TABLE produkt (
  id                             INT AUTO_INCREMENT,
  leverantor_organisationsnummer CHAR(12) NOT NULL,
  namn                           VARCHAR(50),
  beskrivning                    VARCHAR(300),
  provision                      DOUBLE   NOT NULL,
  bildnamn                       VARCHAR(50),
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
  PRIMARY KEY (id, produkt_id),
  FOREIGN KEY (produkt_id) REFERENCES produkt (id)
);

-- bud
CREATE TABLE bud (
  kund_personnummer CHAR(10) NOT NULL,
  auktion_id        INT      NOT NULL,
  belopp            DOUBLE   NOT NULL,
  tid               DATETIME DEFAULT current_timestamp,
  PRIMARY KEY (auktion_id, belopp),
  FOREIGN KEY (kund_personnummer) REFERENCES kund (personnummer),
  FOREIGN KEY (auktion_id) REFERENCES auktion (id)
);

-- avslutade auktioner
CREATE TABLE avslutade_auktioner (
  id                INT NOT NULL AUTO_INCREMENT,
  produkt_id        INT NOT NULL,
  hogsta_bud        DOUBLE,
  kund_personnummer CHAR(10),
  startdatum        DATE,
  slutdatum         DATE,
  utgangspris       DOUBLE,
  acceptpris        DOUBLE,
  PRIMARY KEY (id),
  FOREIGN KEY (produkt_id) REFERENCES produkt (id)
);

-- Procedures

CREATE PROCEDURE lagg_till_produkt(IN in_lev_orgnr CHAR, IN in_namn CHAR, IN in_beskrivning CHAR,
                                   IN in_provision DOUBLE, IN in_bildnamn CHAR)
  BEGIN
    INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, provision, bildnamn) VALUES
      (in_lev_orgnr, in_namn, in_beskrivning, in_provision, in_bildnamn);
  END;

CREATE PROCEDURE lagg_till_leverantor(IN _organisitionsnummer CHAR(12), IN _namn VARCHAR(50),
                                      IN _telefonnummer       VARCHAR(13), IN _epost VARCHAR(50))

  BEGIN

    INSERT INTO leverantor VALUES (_organisitionsnummer, _namn, _telefonnummer, _epost);

  END;

-- Views
CREATE VIEW avslutade_auktioner_utan_kopare AS
  SELECT
    avslutade_auktioner.id AS auctions_id,
    avslutade_auktioner.produkt_id,
    produkt.namn           AS product_namn,
    avslutade_auktioner.acceptpris,
    avslutade_auktioner.utgangspris,
    avslutade_auktioner.startdatum,
    avslutade_auktioner.slutdatum
  FROM avslutade_auktioner
    INNER JOIN produkt ON avslutade_auktioner.produkt_id = produkt.id
  WHERE avslutade_auktioner.hogsta_bud IS NULL AND avslutade_auktioner.kund_personnummer;

-- View för pågående auktioner inklusive högsta bud och budgivare
DROP VIEW IF EXISTS pagaendeauktioner;
CREATE VIEW pagaendeauktioner AS
  SELECT
    kund.personnummer,
    Concat(kund.fornamn, ' ', kund.efternamn) AS namn,
    produkt.namn                              AS produkt,
    auktion.produkt_id,
    b2.hogsta_bud,
    b2.auktion_id,
    bud.tid
  FROM (SELECT DISTINCT
          auktion_id,
          MAX(belopp) AS hogsta_bud
        FROM bud
        GROUP BY auktion_id) AS b2
    INNER JOIN bud ON bud.auktion_id = b2.auktion_id
    INNER JOIN kund ON bud.kund_personnummer = kund.personnummer
    INNER JOIN auktion ON bud.auktion_id = auktion.id
    INNER JOIN produkt ON produkt.id = auktion.produkt_id
  WHERE bud.belopp = b2.hogsta_bud;

SELECT *
FROM pagaendeauktioner;

create view rakna_ut_provision AS
  SELECT avslutade_auktioner.hogsta_bud * produkt.provision from avslutade_auktioner
  INNER JOIN produkt ON avslutade_auktioner.produkt_id = produkt.id;
# Insert DATA time

-- addresser
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Medelstora Torget 1', 10101, 'Everthov');
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Genvägen 12', 10122, 'Tvåskede');
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Högbergs gatan 7', 10562, 'Lågdalen');
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Kvadratvägen 55', 14895, 'Plankholm');
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Valör gatan 100', 16892, 'Njutingö');
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Omvägen 69', 19812, 'Nedsala');
-- kunder
INSERT INTO kund
(personnummer, fornamn, efternamn, telefonnummer, epost, adress_id) VALUES
  ('6808033117', 'Fritte', 'Bohman', '07374826', 'frittw.bohman@domäinen.de', 1),
  ('3212077743', 'Anna', 'Lund', '0743782644', 'hacker.c8s@anon.w', 2),
  ('8707736734', 'Noppe', 'Segelbåt', '0798375892', 'noppe.segelbåt@buissenes.com', 3),
  ('7309824728', 'Limpan', 'Persson', '0734683844', 'limpan123.ha@hotmail.com', 4),
  ('5503047294', 'Edit', 'Gärdeström', '0794782828', 'vadsadu@virus.com', 5),
  ('7706034568', 'Bella', 'Bortskämd', '0783672837', 'Bellam@bloggen.se', 6);

-- leverantorer
INSERT INTO leverantor VALUES ('111111111111', 'Lovely Old Stuff', '0735111111', 'los@sell.se');
INSERT INTO leverantor VALUES ('222222222222', 'Happy Shop', '0735222222', 'hs@sell.se');
INSERT INTO leverantor VALUES ('333333333333', 'Evil Megastore', '0735333333', 'ems@sell.se');
INSERT INTO leverantor VALUES ('444444444444', 'Friendly Old Dude', '0735444444', 'fod@sell.se');
INSERT INTO leverantor VALUES ('555555555555', 'Ms. Butterscotch', '0735555555', 'msb@sell.se');
INSERT INTO leverantor VALUES ('666666666666', 'We Got The Goods', '0735666666', 'wgtg@sell.se');

-- produkter
INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, provision, bildnamn) VALUES
  ('111111111111', 'Ljusstake', ' Ljusstake i silver - tidig barock', 0.3, 'img_1.jpg'),
  ('222222222222', 'Lösnäsa', 'Ansiktsaccessoar för att höja stämningen på kickoffen', 0.1, 'img_2.jpg'),
  ('333333333333', 'Genmodifierad hamster', 'Husdjuret för dig som stimuleras av överlägsenhet', 0.5, 'img_3.jpg'),
  ('444444444444', 'Tavelram', 'Hobby-tillverkad tavelram - 100% ek', 0.25, 'img_4.jpg'),
  ('555555555555', 'Tekopp', 'Klassisk kolonialkopp', 0.35, 'img_5.jpg'),
  ('666666666666', 'Crazy-haze', 'För dig som alltid är sist kvar', 0.1, 'img_5.jpg');

-- auktioner
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
VALUES (1, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
VALUES (2, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
VALUES (3, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
VALUES (4, 3000, 1500, '2017-02-20', '2017-03-20');
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
VALUES (5, 3000, 1500, '2017-02-20', '2017-03-20');

-- bud
INSERT INTO bud (kund_personnummer, auktion_id, belopp) VALUES
  ('6808033117', 1, 1500),
  ('3212077743', 2, 1500),
  ('8707736734', 3, 1500),
  ('7309824728', 4, 1500),
  ('5503047294', 5, 1500),
  ('7706034568', 1, 1501);


SELECT auktion.acceptpris, auktion.acceptpris * produkt.provision FROM auktion INNER JOIN produkt ON auktion.produkt_id = produkt.id;

