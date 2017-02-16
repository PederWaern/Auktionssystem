DROP DATABASE IF EXISTS auktionssystem;
CREATE DATABASE auktionssystem;
USE auktionssystem;
SET GLOBAL event_scheduler = ON;

-- adress
CREATE TABLE adress (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  gata       VARCHAR(50) NOT NULL,
  postnummer CHAR(5)     NOT NULL,
  ort        VARCHAR(50)
);

-- kund
CREATE TABLE kund (
  personnummer  CHAR(10)    NOT NULL,
  fornamn       VARCHAR(50) NOT NULL,
  efternamn     VARCHAR(50) NOT NULL,
  telefonnummer CHAR(13),
  epost         VARCHAR(50) NOT NULL,
  adress_id     INT,
  PRIMARY KEY (personnummer),
  FOREIGN KEY (adress_id) REFERENCES adress (id)
);

-- admin
CREATE TABLE admin (
  anstallningsnummer INT(10) AUTO_INCREMENT,
  personnummer       CHAR(10)    NOT NULL UNIQUE,
  fornamn            VARCHAR(50) NOT NULL,
  efternamn          VARCHAR(50) NOT NULL,
  telefonnummer      CHAR(13)    NOT NULL,
  epost              VARCHAR(50) NOT NULL,
  losenord           VARCHAR(50) NOT NULL,
  adress_id          INT,
  PRIMARY KEY (anstallningsnummer),
  FOREIGN KEY (adress_id) REFERENCES adress (id)
);

-- leverantor
CREATE TABLE leverantor
(
  organisitionsnummer CHAR(12)    NOT NULL,
  namn                VARCHAR(50) NOT NULL,
  telefonnummer       VARCHAR(13),
  epost               VARCHAR(50) NOT NULL,
  provision           DOUBLE      NOT NULL,

  PRIMARY KEY (organisitionsnummer)
);
-- produkt
CREATE TABLE produkt (
  id                             INT AUTO_INCREMENT,
  leverantor_organisationsnummer CHAR(12) NOT NULL,
  namn                           VARCHAR(50),
  beskrivning                    VARCHAR(300),
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
    ON DELETE CASCADE
);

-- avslutade auktioner
CREATE TABLE avslutade_auktioner (
  auktion_id        INT    NOT NULL,
  produkt_id        INT    NOT NULL,
  hogsta_bud        DOUBLE,
  kund_personnummer CHAR(10),
  startdatum        DATE   NOT NULL,
  slutdatum         DATE   NOT NULL,
  utgangspris       DOUBLE NOT NULL,
  acceptpris        DOUBLE,
  datum_sald        DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (auktion_id),
  FOREIGN KEY (produkt_id) REFERENCES produkt (id)
);


/***************************************
    PROCEDURES
***************************************/

/* flytta_pagaende_till_avslutad_auktion */
-- Kopiera relevant data från pågående aktion till avslutad aktion.
-- Ta bort från pågående auktion
DELIMITER //
CREATE PROCEDURE flytta_pagaende_till_avslutad_auktion(IN in_auktion_id INT)
  BEGIN
    INSERT INTO avslutade_auktioner (auktion_id, produkt_id, acceptpris, kund_personnummer, hogsta_bud, startdatum, slutdatum, utgangspris)
      SELECT
        auktion.id,
        produkt_id,
        acceptpris,
        kund_personnummer                                               AS kund,
        (SELECT max(belopp)
         FROM auktion
           LEFT JOIN bud
             ON auktion.id = bud.auktion_id
         WHERE auktion_id = in_auktion_id AND kund_personnummer = kund) AS max,
        startdatum,
        slutdatum,
        utgangspris
      FROM auktion
        LEFT JOIN bud ON auktion.id = bud.auktion_id
      WHERE auktion.id = in_auktion_id
      ORDER BY max DESC
      LIMIT 1;
    DELETE FROM auktion
    WHERE auktion.id = in_auktion_id;
  END //
DELIMITER ;

/* lagg_bud */
DELIMITER //
CREATE PROCEDURE lagg_bud(IN  in_kund_personnummer CHAR(10), IN in_auktion_id INT, IN in_belopp DOUBLE,
                          OUT meddelande           VARCHAR(50))
  BEGIN
    -- Kolla så att budet är större än utgångspris och större än nuvarande maxbud
    IF in_belopp >= (SELECT utgangspris
                     FROM auktion
                     WHERE auktion.id = in_auktion_id)
       AND (in_belopp > (SELECT MAX(bud.belopp)
                         FROM bud
                         WHERE bud.auktion_id = in_auktion_id)
            OR (SELECT MAX(bud.belopp)
                FROM bud
                WHERE bud.auktion_id = in_auktion_id) IS NULL)
    THEN
      -- kolla om budet är över eller lika med accept-priset
      -- lägg budet med samma värde som accept-priset
      -- flytta till avslutade auktioner
      IF in_belopp >= (SELECT auktion.acceptpris
                       FROM auktion
                       WHERE auktion.id = in_auktion_id)
      THEN
        INSERT INTO bud (kund_personnummer, auktion_id, belopp)
        VALUES (in_kund_personnummer, in_auktion_id, (SELECT auktion.acceptpris
                                                      FROM auktion
                                                      WHERE auktion.id = in_auktion_id));
        CALL flytta_pagaende_till_avslutad_auktion(in_auktion_id);
        SET meddelande = 'Produkten köptes för acceptpris';
      ELSE
        INSERT INTO bud (kund_personnummer, auktion_id, belopp) VALUES (in_kund_personnummer, in_auktion_id, in_belopp);
        SET meddelande = 'Budet lades till';
      END IF;
    -- Annars, skriv ut felmeddelande
    ELSE
      SET meddelande = 'Ett fel har uppstått. Inget bud lades till.';
    END IF;
  END //
DELIMITER ;
-- budhistorik for specifik auktion
CREATE PROCEDURE budhistorik_specificerad_auktion(IN in_auktion_id INT)
  BEGIN
    IF EXISTS(SELECT *
              FROM bud
              WHERE auktion_id = in_auktion_id)
    THEN
      SELECT
        kund.fornamn,
        kund.efternamn,
        kund_personnummer,
        bud.belopp,
        bud.tid
      FROM bud
        INNER JOIN Kund ON kund.personnummer = bud.kund_personnummer
      WHERE auktion_id = in_auktion_id;
    ELSE SELECT 'Ingen aktiv auktion hittades';
    END IF;
  END;
-- Lagg till produkt
CREATE PROCEDURE lagg_till_produkt(IN in_lev_orgnr CHAR, IN in_namn CHAR, IN in_beskrivning CHAR, IN in_bildnamn CHAR)
  BEGIN
    INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
      (in_lev_orgnr, in_namn, in_beskrivning, in_bildnamn);
  END;
-- Lagg till leverantor
CREATE PROCEDURE lagg_till_leverantor(IN in_organisitionsnummer CHAR(12), IN in_namn VARCHAR(50),
                                      IN in_telefonnummer       VARCHAR(13), IN in_epost VARCHAR(50),
                                      IN in_provision           DOUBLE,
                                      OUT meddelande            VARCHAR(100))
  BEGIN
    INSERT INTO leverantor VALUES (in_organisitionsnummer, in_namn, in_telefonnummer, in_epost, in_provision);
    SET meddelande = Concat(in_namn, " lades till!");
  END;

-- lägg till auktion procedure
DELIMITER //
CREATE PROCEDURE lagg_till_auktion(IN in_produkt_id INT, IN in_utgangspris DOUBLE, IN in_acceptpris DOUBLE,
                                   IN in_startdatum DATE, IN in_slutdatum DATE, OUT out_date_error_message VARCHAR(100))
  BEGIN
    DECLARE product_exist INT;
    DECLARE product_for_sale INT;
    SELECT COUNT(*)
    INTO product_exist
    FROM produkt
    WHERE id = in_produkt_id;
    IF product_exist != 1
    THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Felaktigt produktnummer'; END IF;
    SELECT COUNT(*)
    INTO product_for_sale
    FROM auktion
    WHERE produkt_id = in_produkt_id;

    IF product_for_sale = 1
    THEN SET out_date_error_message = 'Produkten är redan till försäljning';
    ELSEIF in_startdatum < CURRENT_DATE OR in_slutdatum < CURRENT_DATE OR in_startdatum > in_slutdatum
      THEN SET out_date_error_message = 'Felaktiga datum';
        SELECT out_date_error_message;
    ELSE
      INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
      VALUES (in_produkt_id, in_acceptpris, in_utgangspris, in_startdatum, in_slutdatum);
      SET out_date_error_message = 'Du la till en auktion!';
    END IF;
  END //
DELIMITER ;
-- pagaende auktioner, procedur som visar preliminar provision i kronor samt auktions information innom ett visst tidsintervall
DROP PROCEDURE IF EXISTS provision_pagaende_auktioner_specifierat_tidsintervall;
CREATE PROCEDURE provision_pagaende_auktioner_specifierat_tidsintervall(IN in_slutdatum_start DATE,
                                                                           in_slutdatum_slut  DATE)
  BEGIN
    SELECT
      produkt.namn,
      auktion.id                              AS auktion_id,
      auktion.produkt_id,
      auktion.acceptpris,
      auktion.utgangspris,
      auktion.startdatum,
      auktion.slutdatum,
      b2.hogsta_bud,
      b2.beraknad_provision,
      CONCAT(leverantor.provision * 100, '%') AS provisionsandel
    FROM (
           SELECT
             bud.auktion_id,
             max(bud.belopp)                        AS hogsta_bud,
             max(bud.belopp * leverantor.provision) AS beraknad_provision
           FROM auktion
             INNER JOIN bud ON bud.auktion_id = auktion.id
             INNER JOIN produkt ON auktion.produkt_id = produkt.id
             INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer
           GROUP BY auktion_id) AS b2
      RIGHT JOIN auktion ON auktion.id = b2.auktion_id
      INNER JOIN produkt ON auktion.produkt_id = produkt.id
      INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer
    WHERE auktion.slutdatum BETWEEN in_slutdatum_start AND in_slutdatum_slut;
  END;

-- proc provision på auktioner avslutade mellan specifierat tidsintervall
CREATE PROCEDURE provision_specifierat_tidsintervall(IN in_startdatum DATE, in_slutdatum DATE)
  BEGIN
    SELECT
      avslutade_auktioner.auktion_id,
      (hogsta_bud * leverantor.provision) AS provision
    FROM avslutade_auktioner
      INNER JOIN produkt ON produkt.id = avslutade_auktioner.produkt_id
      INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer
    WHERE slutdatum BETWEEN in_startdatum AND in_slutdatum;
  END;

/***************************************
    EVENTS
***************************************/
-- Event auktion datum check
DELIMITER //
CREATE EVENT auktion_slutdatum_check
  ON SCHEDULE EVERY 10 SECOND
DO
  BEGIN
    DECLARE kontroll_slutford INT DEFAULT FALSE;
    DECLARE kontroll_auktion_id INT;
    DECLARE kontroll_auktion_cursor CURSOR FOR
      SELECT auktion.id
      FROM auktion
      WHERE slutdatum <= current_date;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET kontroll_slutford = TRUE;

    OPEN kontroll_auktion_cursor;
    hamta_auktion_id: LOOP
      FETCH kontroll_auktion_cursor
      INTO kontroll_auktion_id;
      IF kontroll_slutford
      THEN
        LEAVE hamta_auktion_id;
      END IF;
      CALL flytta_pagaende_till_avslutad_auktion(kontroll_auktion_id);
    END LOOP;
  END //
DELIMITER ;
SHOW EVENTS;

/***************************************
    VIEWS
***************************************/
-- View for avslutade auktioner utan kopare
DROP VIEW IF EXISTS avslutade_auktioner_utan_kopare;
CREATE VIEW avslutade_auktioner_utan_kopare AS
  SELECT
    avslutade_auktioner.auktion_id AS auctions_id,
    avslutade_auktioner.produkt_id,
    produkt.namn                   AS product_namn,
    avslutade_auktioner.acceptpris,
    avslutade_auktioner.utgangspris,
    avslutade_auktioner.startdatum,
    avslutade_auktioner.slutdatum
  FROM avslutade_auktioner
    INNER JOIN produkt ON avslutade_auktioner.produkt_id = produkt.id
  WHERE avslutade_auktioner.hogsta_bud IS NULL AND avslutade_auktioner.kund_personnummer IS NULL;

-- View for pågående auktioner inklusive hogsta bud och budgivare
DROP VIEW IF EXISTS pagaende_auktioner;
CREATE VIEW pagaende_auktioner AS
  SELECT
    kund.personnummer,
    Concat(kund.fornamn, ' ', kund.efternamn) AS namn,
    produkt.namn                              AS produkt,
    auktion.produkt_id,
    b2.hogsta_bud,
    b2.auktion_id,
    bud.tid
  FROM (SELECT
          auktion_id,
          MAX(belopp) AS hogsta_bud
        FROM bud
        GROUP BY auktion_id) AS b2
    INNER JOIN bud ON bud.auktion_id = b2.auktion_id
    INNER JOIN kund ON bud.kund_personnummer = kund.personnummer
    INNER JOIN auktion ON bud.auktion_id = auktion.id
    INNER JOIN produkt ON produkt.id = auktion.produkt_id
  WHERE bud.belopp = b2.hogsta_bud;

-- View rakna ut provision TODO - needs oversight
DROP VIEW IF EXISTS rakna_ut_provision;
CREATE VIEW rakna_ut_provision AS
  SELECT avslutade_auktioner.hogsta_bud * leverantor.provision
  FROM avslutade_auktioner
    INNER JOIN produkt ON avslutade_auktioner.produkt_id = produkt.id
    INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer;

-- VIEW - Visa en kundlista på alla kunder som kopt nagot, samt summan for deras totala ordervarde.
CREATE OR REPLACE VIEW total_order_value_per_customer AS
  SELECT
    kund.fornamn,
    kund.efternamn,
    kund_personnummer,
    sum(hogsta_bud) AS total_order_value
  FROM kund
    INNER JOIN avslutade_auktioner ON avslutade_auktioner.kund_personnummer = kund.personnummer
  GROUP BY kund.personnummer;

-- test select
SELECT
  auktion.acceptpris,
  auktion.acceptpris * leverantor.provision
FROM auktion
  INNER JOIN produkt ON auktion.produkt_id = produkt.id
  INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer;

-- VIEW prov per månad
DROP VIEW IF EXISTS provision_per_manad;
CREATE VIEW provision_per_manad AS
  SELECT
    YEAR(datum_sald)                       AS År,
    MONTHNAME(datum_sald)                  AS Månad,
    sum(hogsta_bud * leverantor.provision) AS Provision
  FROM avslutade_auktioner
    INNER JOIN produkt ON produkt.id = avslutade_auktioner.produkt_id
    INNER JOIN leverantor ON produkt.leverantor_organisationsnummer = leverantor.organisitionsnummer
  GROUP BY År, Månad;

USE auktionssystem;

# Insert DATA time

-- addresser
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Medelstora Torget 1', 10101, 'Everthov'),
  ('Genvägen 12', 10122, 'Tvåskede'),
  ('Högbergs gatan 7', 10562, 'Lågdalen'),
  ('Kvadratvägen 55', 14895, 'Plankholm'),
  ('Valör gatan 100', 16892, 'Njutingö'),
  ('Omvägen 69', 19812, 'Nedsala'),
  ('Östermalmstorg 53', 54634, 'Stockholm');

-- kunder
INSERT INTO kund (personnummer, fornamn, efternamn, telefonnummer, epost, adress_id) VALUES
  ('6808033117', 'Fritte', 'Bohman', '0737482653', 'frittw.bohman@domäinen.de',  1),
  ('3212077743', 'Anna', 'Lund', '0743782644', 'hacker.c8s@anon.w', 2),
  ('8707736734', 'Noppe', 'Segelbåt', '0798375892', 'noppe.segelbåt@buissenes.com', 3),
  ('7309824728', 'Limpan', 'Persson', '0734683844', 'limpan123.ha@hotmail.com',  4),
  ('5503047294', 'Edit', 'Gärdeström', '0794782828', 'vadsadu@virus.com', 5),
  ('7706034568', 'Bella', 'Bortskämd', '0783672837', 'Bellam@bloggen.se',  6);

  -- admins
INSERT INTO admin (personnummer, fornamn, efternamn, telefonnummer, epost, losenord, adress_id) VALUES
  ('1703052157', 'Gunnar', 'Tillhamre', '0738463987', 'gunnar.tillhamre@domäinen.de', '111', 7);

-- leverantorer
INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision) VALUES
  ('111111111111', '0735111111', 'los@sell.se', 'Lovely Old Stuff', 0.3),
  ('222222222222', '0735222222', 'hs@sell.se', 'Happy Shop', 0.1),
  ('333333333333', '0735333333', 'ems@sell.se', 'Evil Megastore', 0.5),
  ('444444444444', '0735444444', 'fod@sell.se', 'Friendly Old Dude', 0.25),
  ('555555555555', '0735555555', 'msb@sell.se', 'Ms. Butterscotch', 0.35),
  ('666666666666', '0735666666', 'wgtg@sell.se', 'We Got The Goods', 0.1);

-- produkter
INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
  ('111111111111', 'Ljusstake', ' Ljusstake i silver - tidig barock', 'img_1.jpg'),
  ('222222222222', 'Lösnäsa', 'Ansiktsaccessoar för att höja stämningen på kickoffen', 'img_2.jpg'),
  ('333333333333', 'Genmodifierad hamster', 'Husdjuret för dig som stimuleras av överlägsenhet', 'img_3.jpg'),
  ('444444444444', 'Tavelram', 'Hobby-tillverkad tavelram - 100% ek', 'img_4.jpg'),
  ('555555555555', 'Tekopp', 'Klassisk kolonialkopp', 'img_5.jpg'),
  ('666666666666', 'Crazy-haze', 'För dig som alltid är sist kvar', 'img_5.jpg'),
  ('666666666666', 'Soffa', '90-tal IKEA', 'img_6.jpg'),
  ('666666666666', 'Fåtölj', 'Vintage, superskön', 'img_7.jpg'),
  ('111111111111', 'Leksaksbil', 'Engelsk, vintage', 'img_8.jpg'),
  ('111111111111', 'Uppstoppad räv', 'Transylvansk', 'img_9.jpg');

-- auktioner
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES
  (1, 3000, 1500, '2017-02-20', '2017-03-20'),
  (2, 3000, 1500, '2017-02-20', '2017-03-20'),
  (3, 3000, 1500, '2017-02-20', '2017-03-20'),
  (4, 3000, 1500, '2017-02-20', '2017-03-20'),
  (5, 3000, 1500, '2017-01-20', '2017-02-13'),
  (6, 3000, 2500, '2017-01-20', '2017-02-14'),
  (7, 3000, 1000, '2017-01-01', '2017-02-01'),
  (8, 3000, 1000, '2017-01-01', '2017-02-01'),
  (9, 3000, 1000, '2017-01-01', '2017-02-01'),
  (10, 3000, 1000, '2017-01-01', '2017-02-01');


-- bud
INSERT INTO bud (kund_personnummer, auktion_id, belopp) VALUES
  ('6808033117', 1, 1500),
  ('3212077743', 2, 1500),
  ('8707736734', 3, 1500),
  ('7309824728', 4, 1500),
  ('5503047294', 5, 1500),
  ('7706034568', 7, 2000),
  ('7706034568', 8, 1520),
  ('7706034568', 1, 2000),
  ('3212077743', 9, 2500),
  ('8707736734', 10, 2100);

