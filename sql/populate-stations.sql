INSERT INTO stations
VALUES
    (1,  "Cracow"),
    (2,  "Warsaw"),
    (3,  "Wroclaw"),
    (4,  "Rzeszow"),
    (5,  "Przemysl"),
    (6,  "Lublin"),
    (7,  "Poznan"),
    (8,  "Zakopane"),
    (9,  "Zielona Gora"),
    (10, "Gdansk"),
    (11, "Szczecin"),
    (12, "Bialystok"),
    (13, "Olsztyn"),
    (14, "Opole"),
    (15, "Katowice"),
    (16, "Bydgoszcz"),
    (17, "Lodz"),
    (18, "Kielce"),
    (19, "Czestochowa");

INSERT INTO station_connections (id, from_id, to_id, weight)
VALUES
    (1,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Katowice"),      67),	-- Cracow-Katowice
    (2,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Zakopane"),      115), 	-- Cracow-Zakopane
    (3,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Kielce"),        91),	-- Cracow-Kielce
    (4,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Rzeszow"),       100),	-- Cracow-Rzeszow
    (5,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lodz"),          73),	-- Warsaw-Lodz
    (6,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Poznan"),        179),	-- Warsaw-Poznan
    (7,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     179),	-- Warsaw-Bydgoszcz
    (8,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Gdansk"),        161),	-- Warsaw-Gdansk
    (9,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       152),	-- Warsaw-Olsztyn
    (10, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bialystok"),     172),	-- Warsaw-Bialystok
    (11, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lublin"),        130),	-- Warsaw-Lublin
    (12, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   133),	-- Warsaw-Czestochowa
    (13, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Kielce"),        147),	-- Warsaw-Kielce
    (14, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Opole"),         46),	-- Wroclaw-Opole
    (15, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Poznan"),        102),	-- Wroclaw-Poznan
    (16, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Zielona Gora"),  104),   -- Wroclaw-Zielona Gora
    (17, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Lodz"),          217),	-- Wroclaw-Lodz
    (18, (SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Przemysl"),      70),	-- Rzeszow-Przemysl
    (19, (SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Lublin"),        158),	-- Rzeszow-Lublin
    (20, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Zielona Gora"),  96),	-- Poznan-Zielona Gora
    (21, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Lodz"),          189),	-- Poznan-Lodz
    (22, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     94),	-- Poznan-Bydgoszcz
    (23, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Szczecin"),      190),	-- Poznan-Szczecin
    (24, (SELECT id FROM stations WHERE name = "Zielona Gora"), (SELECT id FROM stations WHERE name = "Szczecin"),      186),	-- Zielona Gora-Szczecin
    (25, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Szczecin"),      281),	-- Gdansk-Szczecin
    (26, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     91),	-- Gdansk-Bydgoszcz
    (27, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       138),	-- Gdansk-Olsztyn
    (28, (SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Bydgoszcz"),     282),	-- Szczecin-Bydgoszcz
    (29, (SELECT id FROM stations WHERE name = "Bialystok"),    (SELECT id FROM stations WHERE name = "Olsztyn"),       277),	-- Bialystok-Olsztyn
    (30, (SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Bydgoszcz"),     194),	-- Olsztyn-Bydgoszcz
    (31, (SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Katowice"),      79),	-- Opole-Katowice
    (32, (SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Czestochowa"),   61),	-- Opole-Czestochowa
    (33, (SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Kielce"),        118),	-- Katowice-Kielce
    (34, (SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Czestochowa"),   70),    -- Katowice-Czestochowa
    (35, (SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Lodz"),          189),	-- Bydgoszcz-Lodz
    (36, (SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Kielce"),        205),	-- Lodz-Kielce
    (37, (SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Czestochowa"),   90),	-- Lodz-Czestochowa
    (38, (SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   104);	-- Kielce-Czestochowa