INSERT INTO users (id, name, pass, state, nickname, email) VALUES (1, 'Anton', '1', 1, '1', '1');
INSERT INTO users (id, name, pass, state, nickname, email) VALUES (2, 'Dima', '2', 2, '2', '2');
INSERT INTO friendships (user1, user2) VALUES (1, 2);
INSERT INTO goods (id, name, description, location, cost, currency) VALUES (1, 'Computer', 'Up-to-date', 'Germany', 1, 'RUR');
INSERT INTO trips (id, dest, departure_date, arrival_date, traveller, capacity) VALUES (1, 'Germany', TO_DATE ('30.10.2014', 'DD.MM.YYYY'), TO_DATE ('01.11.2014', 'DD.MM.YYYY'), 2, 2);
INSERT INTO requests (trip, goods, customer, count) VALUES (1, 1, 1, 1);
INSERT INTO snetworks (user_id, network_type, page) VALUES (1, 1, '1');
commit;