INSERT INTO museums (name, address, phone, founded) VALUES ('Louvre Museum', 'Rue de Rivoli, 75001 Paris, France', '+33 1 40 20 50 50', '1793');
INSERT INTO museums (name, address, phone, founded) VALUES ('The Metropolitan Museum of Art', '1000 5th Ave, New York, NY 10028, United States', '+1 212-535-7710', '1870');
INSERT INTO museums (name, address, phone, founded) VALUES ('Vatican Museums', '00120 Vatican City', '+39 06 6988 4676', '1506');

INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Mona Lisa', 'Leonardo da Vinci', 'Renaissance', 'Oil on poplar panel', 1);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Winged Victory of Samothrace', 'Unknown', 'Ancient Greece', 'Parian marble', 1);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Liberty Leading the People', 'Eugene Delacroix', 'Romanticism', 'Oil on canvas', 1);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('The Seated Scribe', 'Unknown', 'Ancient Egypt', 'limestone, quartz, copper', 1);

INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Washington Crossing the Delaware', 'Emanuel Leutze', 'History painting', 'Oil Paint', 2);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Self-Portrait with a Straw Hat', 'Vincent van Gogh', 'Post-Impressionism', 'Oil Paint', 2);

INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Laocoon and His Sons', 'Agesander of Rhodes, Athenodoros of Rhodes, Polydorus of Rhodes', 'Ancient Greece', 'Marble', 3);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('The Last Judgment', 'Michelangelo', 'Renaissance', 'Paint, Plaster', 3);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('Sistine Chapel ceiling', 'Michelangelo', 'Renaissance', 'Gold, Plaster', 3);
INSERT INTO works (name, artist, period, medium, museum_id) VALUES ('The Fire in the Borgo', 'Giulio Romano', 'History painting', '', 3);