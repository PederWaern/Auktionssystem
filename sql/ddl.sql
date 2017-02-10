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

-- leverantor

-- produkt

-- auktion

-- bud

-- avslutade auktioner