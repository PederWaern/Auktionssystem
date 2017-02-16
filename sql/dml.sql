USE auktionssystem;

-- Registrera produkt --

INSERT INTO produkt (leverantor_organisationsnummer, namn, beskrivning, bildnamn)
VALUES ('111111111111', 'Badanka', 'Gul', NULL);


-- Skapa en auktion --
SET @msg = "";
CALL lagg_till_auktion(11, 2000, 3000, '2017-02-16', '2017-03-16', @msg);

SELECT * FROM auktion;


-- Visa pågående auktioner samt högsta bud --
SELECT * FROM pagaende_auktioner;


-- Se budhistorik för en specifik auktion --
CALL budhistorik_specificerad_auktion(1);


-- Vilka auktioner avslutas inom ett visst datumontervall
CALL provision_pagaende_auktioner_specifierat_tidsintervall('2017-01-01', '2017-03-23');


-- Auktion som avslutats hamnar i avslutade auktioner, se event i ddl


-- Visa en kundlista på kunder som köpt något
SELECT * FROM total_order_value_per_customer;


-- Total provisionen per månad --

SELECT * FROM provision_per_manad;