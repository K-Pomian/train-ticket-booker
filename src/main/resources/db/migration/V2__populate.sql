USE `train-ticket-booker-test`;

INSERT INTO stations (name)
VALUES
    ("Warsaw"),
    ("Cracow"),
    ("Poznan"),
    ("Wroclaw"),
    ("Gdansk");

INSERT INTO station_connections (price_weight, time_weight, from_station, to_station)
VALUES
    (50, 130, 1, 2),
    (45, 110, 1, 3),
    (42, 100, 1, 5),
    (50, 130, 2, 1),
    (52, 135, 2, 4),
    (45, 110, 3, 1),
    (51, 159, 3, 4),
    (49, 127, 3, 5),
    (52, 135, 4, 2),
    (51, 159, 4, 3),
    (42, 100, 5, 1),
    (49, 127, 5, 3);

INSERT INTO users (email, password, username)
VALUES
    ("gucio123@gmail.com", "de8ac2bfc88122508f4404b9dad88e85a84de01f02bac322188bc72c052e1869", "gucio123"),
    ("gucinski321@gmail.com", "76e8a8404ed5f9036a144c16f3b4e37e3dc17a56bde9d78391c979e98fc705ae", "gucinski321");

INSERT INTO tickets (ticket_id, booking_time, carriage, discount, price, seat, travel_time, station_from_id, station_to_id, user_id)
VALUES
    ("24dfe11c-5350-47d4-ab8e-6c46e89c7923", "2023-10-30 12:55:04", 12, 0, 50, 51, "2023-10-31 14:35:00", 1, 2, 1),
    ("d73ba5b0-8d8b-4840-87d1-32c9ef9acba4", "2023-10-30 12:55:04", 11, 0, 52, 12, "2023-10-31 14:52:00", 2, 4, 1),
    ("5e899848-6d1c-4cb6-a8a8-729660d32b1d", "2023-10-30 13:12:42", 11, 1, 25.5, 51, "2023-10-31 14:35:00", 1, 2, 2);