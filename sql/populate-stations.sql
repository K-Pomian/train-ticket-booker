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

INSERT INTO station_connections (id, from_id, to_id, time_weight, price_weight)
VALUES
    (1,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Katowice"),      67,     25),	-- Cracow-Katowice
    (2,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Zakopane"),      115,    36), 	-- Cracow-Zakopane
    (3,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Kielce"),        91,     31),	-- Cracow-Kielce
    (4,  (SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Rzeszow"),       100,    33),	-- Cracow-Rzeszow
    (5,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lodz"),          73,     26),	-- Warsaw-Lodz
    (6,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Poznan"),        179,    52),	-- Warsaw-Poznan
    (7,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     179,    52),	-- Warsaw-Bydgoszcz
    (8,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Gdansk"),        161,    49),	-- Warsaw-Gdansk
    (9,  (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       152,    46),	-- Warsaw-Olsztyn
    (10, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bialystok"),     172,    51),	-- Warsaw-Bialystok
    (11, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lublin"),        130,    40),	-- Warsaw-Lublin
    (12, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   133,    41),	-- Warsaw-Czestochowa
    (13, (SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Kielce"),        147,    45),	-- Warsaw-Kielce
    (14, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Opole"),         46,     17),	-- Wroclaw-Opole
    (15, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Poznan"),        102,    34),	-- Wroclaw-Poznan
    (16, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Zielona Gora"),  104,    34),    -- Wroclaw-Zielona Gora
    (17, (SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Lodz"),          217,    59),	-- Wroclaw-Lodz
    (18, (SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Przemysl"),      70,     25),	-- Rzeszow-Przemysl
    (19, (SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Lublin"),        158,    47),	-- Rzeszow-Lublin
    (20, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Zielona Gora"),  96,     33),	-- Poznan-Zielona Gora
    (21, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Lodz"),          189,    54),	-- Poznan-Lodz
    (22, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     94,     31),	-- Poznan-Bydgoszcz
    (23, (SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Szczecin"),      190,    54),	-- Poznan-Szczecin
    (24, (SELECT id FROM stations WHERE name = "Zielona Gora"), (SELECT id FROM stations WHERE name = "Szczecin"),      186,    54),	-- Zielona Gora-Szczecin
    (25, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Szczecin"),      281,    67),	-- Gdansk-Szczecin
    (26, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     91,     31),	-- Gdansk-Bydgoszcz
    (27, (SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       138,    42),	-- Gdansk-Olsztyn
    (28, (SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Bydgoszcz"),     282,    67),	-- Szczecin-Bydgoszcz
    (29, (SELECT id FROM stations WHERE name = "Bialystok"),    (SELECT id FROM stations WHERE name = "Olsztyn"),       277,    66),	-- Bialystok-Olsztyn
    (30, (SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Bydgoszcz"),     194,    55),	-- Olsztyn-Bydgoszcz
    (31, (SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Katowice"),      79,     27),	-- Opole-Katowice
    (32, (SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Czestochowa"),   61,     24),	-- Opole-Czestochowa
    (33, (SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Kielce"),        118,    37),	-- Katowice-Kielce
    (34, (SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Czestochowa"),   70,     25),    -- Katowice-Czestochowa
    (35, (SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Lodz"),          189,    54),	-- Bydgoszcz-Lodz
    (36, (SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Kielce"),        205,    58),	-- Lodz-Kielce
    (37, (SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Czestochowa"),   90,     29),	-- Lodz-Czestochowa
    (38, (SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   104,    34);	-- Kielce-Czestochowa