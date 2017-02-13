USE auktionssystem;
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
INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('111111111111', '0735111111', 'los@sell.se', 'Lovely Old Stuff', 0.3);

INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('222222222222', '0735222222', 'hs@sell.se', 'Happy Shop', 0.1);

INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('333333333333', '0735333333', 'ems@sell.se', 'Evil Megastore', 0.5);

INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('444444444444', '0735444444', 'fod@sell.se', 'Friendly Old Dude', 0.25);

INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('555555555555', '0735555555', 'msb@sell.se', 'Ms. Butterscotch', 0.35);

INSERT INTO leverantor (organisitionsnummer, telefonnummer, epost, namn, provision)
VALUES ('666666666666', '0735666666', 'wgtg@sell.se', 'We Got The Goods', 0.1);

-- produkter
INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
  ('111111111111', 'Ljusstake', ' Ljusstake i silver - tidig barock', 'img_1.jpg'),
  ('222222222222', 'Lösnäsa', 'Ansiktsaccessoar för att höja stämningen på kickoffen', 'img_2.jpg'),
  ('333333333333', 'Genmodifierad hamster', 'Husdjuret för dig som stimuleras av överlägsenhet', 'img_3.jpg'),
  ('444444444444', 'Tavelram', 'Hobby-tillverkad tavelram - 100% ek', 'img_4.jpg'),
  ('555555555555', 'Tekopp', 'Klassisk kolonialkopp', 'img_5.jpg'),
  ('666666666666', 'Crazy-haze', 'För dig som alltid är sist kvar', 'img_5.jpg');

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
