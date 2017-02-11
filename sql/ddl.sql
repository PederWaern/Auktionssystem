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

-- lägg till auktion procedure
DELIMITER //
CREATE PROCEDURE lägg_till_auktion(IN in_produkt_id INT, IN in_utgangspris INT, IN in_acceptpris INT,
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
    SET MESSAGE_TEXT = 'Please enter a valid product number'; END IF;
    SELECT COUNT(*)
    INTO product_for_sale
    FROM auktion
    WHERE produkt_id = in_produkt_id;

    IF product_for_sale = 1
    THEN SET out_date_error_message = 'The product is alredy for sale';
    ELSEIF in_startdatum < CURRENT_DATE OR in_slutdatum < CURRENT_DATE OR in_startdatum > in_slutdatum
      THEN SET out_date_error_message = 'dates are incorrect';
        SELECT out_date_error_message;
    ELSE
      INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum)
      VALUES (in_produkt_id, in_acceptpris, in_utgangspris, in_startdatum, in_slutdatum);
    END IF;
  END //
DELIMITER ;

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

-- VIEW - Visa en kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.
CREATE OR REPLACE VIEW total_order_value_per_customer AS
SELECT kund.fornamn, kund.efternamn, kund_personnummer, sum(hogsta_bud) AS total_order_value FROM kund
INNER JOIN avslutade_auktioner ON avslutade_auktioner.kund_personnummer = kund.personnummer
GROUP BY kund.personnummer;




SELECT auktion.acceptpris, auktion.acceptpris * produkt.provision FROM auktion INNER JOIN produkt ON auktion.produkt_id = produkt.id;

