-- V2__populate_db.sql

INSERT INTO planet (id, name) VALUES ('MARS', 'Mars');
INSERT INTO planet (id, name) VALUES ('VEN', 'Venus');
INSERT INTO planet (id, name) VALUES ('EARTH', 'Earth');
INSERT INTO planet (id, name) VALUES ('JUP', 'Jupiter');
INSERT INTO planet (id, name) VALUES ('SAT', 'Saturn');


INSERT INTO client (name) VALUES ('John Doe');
INSERT INTO client (name) VALUES ('Jane Smith');
INSERT INTO client (name) VALUES ('Bob Johnson');
INSERT INTO client (name) VALUES ('Alice Brown');
INSERT INTO client (name) VALUES ('Charlie Davis');
INSERT INTO client (name) VALUES ('Eve Miller');
INSERT INTO client (name) VALUES ('David Wilson');
INSERT INTO client (name) VALUES ('Grace Lee');
INSERT INTO client (name) VALUES ('Hannah Moore');
INSERT INTO client (name) VALUES ('Jack Taylor');


INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (1, 'EARTH', 'MARS');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (2, 'VEN', 'JUP');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (3, 'MARS', 'SAT');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (4, 'EARTH', 'SAT');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (5, 'VEN', 'EARTH');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (6, 'JUP', 'MARS');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (7, 'SAT', 'VEN');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (8, 'MARS', 'EARTH');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (9, 'EARTH', 'VEN');
INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (10, 'SAT', 'JUP');
