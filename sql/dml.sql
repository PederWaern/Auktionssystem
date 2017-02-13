USE auktionssystem;

# Insert DATA time

-- addresser
INSERT INTO adress (gata, postnummer, ort) VALUES
  ('Medelstora Torget 1', 10101, 'Everthov'),
  ('Genvägen 12', 10122, 'Tvåskede'),
  ('Högbergs gatan 7', 10562, 'Lågdalen'),
  ('Kvadratvägen 55', 14895, 'Plankholm'),
  ('Valör gatan 100', 16892, 'Njutingö'),
  ('Omvägen 69', 19812, 'Nedsala');

-- kunder
INSERT INTO kund (personnummer, fornamn, efternamn, telefonnummer, epost, losenord, adress_id) VALUES
  ('6808033117', 'Fritte', 'Bohman', '07374826', 'frittw.bohman@domäinen.de', '111', 1),
  ('3212077743', 'Anna', 'Lund', '0743782644', 'hacker.c8s@anon.w', '222', 2),
  ('8707736734', 'Noppe', 'Segelbåt', '0798375892', 'noppe.segelbåt@buissenes.com', '333', 3),
  ('7309824728', 'Limpan', 'Persson', '0734683844', 'limpan123.ha@hotmail.com', '444', 4),
  ('5503047294', 'Edit', 'Gärdeström', '0794782828', 'vadsadu@virus.com', '555', 5),
  ('7706034568', 'Bella', 'Bortskämd', '0783672837', 'Bellam@bloggen.se', '666', 6);

-- leverantorer
INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, losenord, namn, provision) VALUES
  ('111111111111', '0735111111', 'los@sell.se', '111', 'Lovely Old Stuff', 0.3),
  ('222222222222', '0735222222', 'hs@sell.se', '222', 'Happy Shop', 0.1),
  ('333333333333', '0735333333', 'ems@sell.se', '333', 'Evil Megastore', 0.5),
  ('444444444444', '0735444444', 'fod@sell.se', '444', 'Friendly Old Dude', 0.25),
  ('555555555555', '0735555555', 'msb@sell.se', '555', 'Ms. Butterscotch', 0.35),
  ('666666666666', '0735666666', 'wgtg@sell.se', '666', 'We Got The Goods', 0.1);

-- produkter
INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
  ('111111111111', 'Ljusstake', ' Ljusstake i silver - tidig barock', 'img_1.jpg'),
  ('222222222222', 'Lösnäsa', 'Ansiktsaccessoar för att höja stämningen på kickoffen', 'img_2.jpg'),
  ('333333333333', 'Genmodifierad hamster', 'Husdjuret för dig som stimuleras av överlägsenhet', 'img_3.jpg'),
  ('444444444444', 'Tavelram', 'Hobby-tillverkad tavelram - 100% ek', 'img_4.jpg'),
  ('555555555555', 'Tekopp', 'Klassisk kolonialkopp', 'img_5.jpg'),
  ('666666666666', 'Crazy-haze', 'För dig som alltid är sist kvar', 'img_5.jpg');

-- auktioner
INSERT INTO auktion (produkt_id, acceptpris, utgangspris, startdatum, slutdatum) VALUES
  (1, 3000, 1500, '2017-02-20', '2017-03-20'),
  (2, 3000, 1500, '2017-02-20', '2017-03-20'),
  (3, 3000, 1500, '2017-02-20', '2017-03-20'),
  (4, 3000, 1500, '2017-02-20', '2017-03-20'),
  (5, 3000, 1500, '2017-01-20', '2017-02-13'),
  (6, 3000, 2500, '2017-01-20', '2017-02-14');

-- bud
INSERT INTO bud (kund_personnummer, auktion_id, belopp) VALUES
  ('6808033117', 1, 1500),
  ('3212077743', 2, 1500),
  ('8707736734', 3, 1500),
  ('7309824728', 4, 1500),
  ('5503047294', 5, 1500),
  ('7706034568', 1, 1501);

-- test select
SELECT *
FROM rakna_ut_provision;
SELECT *
FROM avslutade_auktioner;
SELECT *
FROM avslutade_auktioner_utan_kopare;
SELECT *
FROM auktion;


/**********************
  JAVAFUNKTIONALITET
***********************/

-- registrera en produkt
  INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
  ('?','?','?');

 -- lägg till auktion

call lägg_till_auktion('?','?','?','?','?','?','?');

-- lista pågående auktioner

SELECT * from pagaende_auktioner;

-- se budhistorik för en viss auktion samt vilka kunder som lagt bud

call budhistorik_specificerad_auktion('?');

-- Vilka auktioner avslutas inom ett visst datum intervall och vad blir provisionen

call provision_specifierat_tidsintervall('?', '?');

-- Visa view på avslutade auktioner utan köpare

SELECT * from avslutade_auktioner_utan_kopare;

-- visa kundlista med kunder som köpt något samt deras totala ordervärde är

SELECT * from total_order_value_per_customer;

-- vad är den totala provisionen per månad

SELECT * from provision_per_manad;




  