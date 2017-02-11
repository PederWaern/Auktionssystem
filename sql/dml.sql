USE auktionssystem;
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

-- VIEW - Visa en kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.
CREATE OR REPLACE VIEW total_order_value_per_customer AS
SELECT kund.fornamn, kund.efternamn, kund_personnummer, sum(hogsta_bud) AS total_order_value FROM kund
INNER JOIN avslutade_auktioner ON avslutade_auktioner.kund_personnummer = kund.personnummer
GROUP BY kund.personnummer;
