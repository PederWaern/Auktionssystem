-- test select
SELECT *
FROM rakna_ut_provision;

SELECT * from bud;

SELECT * FROM avslutade_auktioner;
SELECT *
FROM avslutade_auktioner_utan_kopare;
SELECT * FROM auktion;
SELECT * from provision_per_manad;


/**********************
  JAVAFUNKTIONALITET
***********************/

-- registrera en produkt
  INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn) VALUES
  (?,?,?,?);

 -- lägg till auktion

call lagg_till_auktion (?,?,?,?,?,?)

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



Call budhistorik_specificerad_auktion(1);
