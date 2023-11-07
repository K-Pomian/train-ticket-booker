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

INSERT INTO station_connections (from_station, to_station, time_weight, price_weight)
VALUES
    ((SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Katowice"),      67,     25),	-- Cracow-Katowice
    ((SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Zakopane"),      115,    36), 	-- Cracow-Zakopane
    ((SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Kielce"),        91,     31),	-- Cracow-Kielce
    ((SELECT id FROM stations WHERE name = "Cracow"),       (SELECT id FROM stations WHERE name = "Rzeszow"),       100,    33),	-- Cracow-Rzeszow
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lodz"),          73,     26),	-- Warsaw-Lodz
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Poznan"),        179,    52),	-- Warsaw-Poznan
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     179,    52),	-- Warsaw-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Gdansk"),        161,    49),	-- Warsaw-Gdansk
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       152,    46),	-- Warsaw-Olsztyn
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Bialystok"),     172,    51),	-- Warsaw-Bialystok
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Lublin"),        130,    40),	-- Warsaw-Lublin
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   133,    41),	-- Warsaw-Czestochowa
    ((SELECT id FROM stations WHERE name = "Warsaw"),       (SELECT id FROM stations WHERE name = "Kielce"),        147,    45),	-- Warsaw-Kielce
    ((SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Opole"),         46,     17),	-- Wroclaw-Opole
    ((SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Poznan"),        102,    34),	-- Wroclaw-Poznan
    ((SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Zielona Gora"),  104,    34),    -- Wroclaw-Zielona Gora
    ((SELECT id FROM stations WHERE name = "Wroclaw"),      (SELECT id FROM stations WHERE name = "Lodz"),          217,    59),	-- Wroclaw-Lodz
    ((SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Cracow"),        100,    33),	-- Rzeszow-Cracow
    ((SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Przemysl"),      70,     25),	-- Rzeszow-Przemysl
    ((SELECT id FROM stations WHERE name = "Rzeszow"),      (SELECT id FROM stations WHERE name = "Lublin"),        158,    47),	-- Rzeszow-Lublin
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Warsaw"),        179,    52),	-- Poznan-Warsaw
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Wroclaw"),       102,    34),	-- Poznan-Wroclaw
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Zielona Gora"),  96,     33),	-- Poznan-Zielona Gora
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Lodz"),          189,    54),	-- Poznan-Lodz
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     94,     31),	-- Poznan-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Poznan"),       (SELECT id FROM stations WHERE name = "Szczecin"),      190,    54),	-- Poznan-Szczecin
    ((SELECT id FROM stations WHERE name = "Zielona Gora"), (SELECT id FROM stations WHERE name = "Wroclaw"),       104,    34),	-- Zielona Gora-Wroclaw
    ((SELECT id FROM stations WHERE name = "Zielona Gora"), (SELECT id FROM stations WHERE name = "Poznan"),        96,     33),	-- Zielona Gora-Poznan
    ((SELECT id FROM stations WHERE name = "Zielona Gora"), (SELECT id FROM stations WHERE name = "Szczecin"),      186,    54),	-- Zielona Gora-Szczecin
    ((SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Warsaw"),        161,    49),	-- Gdansk-Warsaw
    ((SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Szczecin"),      281,    67),	-- Gdansk-Szczecin
    ((SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Bydgoszcz"),     91,     31),	-- Gdansk-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Gdansk"),       (SELECT id FROM stations WHERE name = "Olsztyn"),       138,    42),	-- Gdansk-Olsztyn
    ((SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Poznan"),        190,    54),	-- Szczecin-Poznan
    ((SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Zielona Gora"),  186,    54),	-- Szczecin-Zielona Gora
    ((SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Gdansk"),        281,    67),	-- Szczecin-Gdansk
    ((SELECT id FROM stations WHERE name = "Szczecin"),     (SELECT id FROM stations WHERE name = "Bydgoszcz"),     282,    67),	-- Szczecin-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Bialystok"),    (SELECT id FROM stations WHERE name = "Warsaw"),        172,    51),	-- Bialystok-Warsaw
    ((SELECT id FROM stations WHERE name = "Bialystok"),    (SELECT id FROM stations WHERE name = "Olsztyn"),       277,    66),	-- Bialystok-Olsztyn
    ((SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Warsaw"),        152,    46),	-- Olsztyn-Warsaw
    ((SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Gdansk"),        138,    42),	-- Olsztyn-Gdansk
    ((SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Bialystok"),     277,    66),	-- Olsztyn-Bialystok
    ((SELECT id FROM stations WHERE name = "Olsztyn"),      (SELECT id FROM stations WHERE name = "Bydgoszcz"),     194,    55),	-- Olsztyn-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Wroclaw"),       46,     17),	-- Opole-Wroclaw
    ((SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Katowice"),      79,     27),	-- Opole-Katowice
    ((SELECT id FROM stations WHERE name = "Opole"),        (SELECT id FROM stations WHERE name = "Czestochowa"),   61,     24),	-- Opole-Czestochowa
    ((SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Cracow"),        67,     25),	-- Katowice-Cracow
    ((SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Opole"),         79,     27),	-- Katowice-Opole
    ((SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Kielce"),        118,    37),	-- Katowice-Kielce
    ((SELECT id FROM stations WHERE name = "Katowice"),     (SELECT id FROM stations WHERE name = "Czestochowa"),   70,     25),    -- Katowice-Czestochowa
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Warsaw"),        179,    52),	-- Bydgoszcz-Warsaw
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Poznan"),        94,     31),	-- Bydgoszcz-Poznan
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Gdansk"),        91,     31),	-- Bydgoszcz-Gdansk
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Szczecin"),      282,    67),	-- Bydgoszcz-Szczecin
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Olsztyn"),       194,    55),	-- Bydgoszcz-Olsztyn
    ((SELECT id FROM stations WHERE name = "Bydgoszcz"),    (SELECT id FROM stations WHERE name = "Lodz"),          189,    54),	-- Bydgoszcz-Lodz
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Warsaw"),        73,     26),	-- Lodz-Warsaw
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Wroclaw"),       217,    59),	-- Lodz-Wroclaw
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Poznan"),        189,    54),	-- Lodz-Poznan
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Bydgoszcz"),     189,    54),	-- Lodz-Bydgoszcz
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Kielce"),        205,    58),	-- Lodz-Kielce
    ((SELECT id FROM stations WHERE name = "Lodz"),         (SELECT id FROM stations WHERE name = "Czestochowa"),   90,     29),	-- Lodz-Czestochowa
    ((SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Cracow"),        91,     31),	-- Kielce-Cracow
    ((SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Warsaw"),        147,    45),	-- Kielce-Warsaw
    ((SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Katowice"),      118,    37),	-- Kielce-Katowice
    ((SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Lodz"),          205,    58),	-- Kielce-Lodz
    ((SELECT id FROM stations WHERE name = "Kielce"),       (SELECT id FROM stations WHERE name = "Czestochowa"),   104,    34),	-- Kielce-Czestochowa
    ((SELECT id FROM stations WHERE name = "Zakopane"),     (SELECT id FROM stations WHERE name = "Cracow"),        115,    36),	-- Zakopane-Cracow
    ((SELECT id FROM stations WHERE name = "Lublin"),       (SELECT id FROM stations WHERE name = "Warsaw"),        130,    40),	-- Lublin-Warsaw
    ((SELECT id FROM stations WHERE name = "Lublin"),       (SELECT id FROM stations WHERE name = "Rzeszow"),       458,    47),	-- Lublin-Rzeszow
    ((SELECT id FROM stations WHERE name = "Czestochowa"),  (SELECT id FROM stations WHERE name = "Warsaw"),        133,    41),	-- Czestochowa-Warsaw
    ((SELECT id FROM stations WHERE name = "Czestochowa"),  (SELECT id FROM stations WHERE name = "Opole"),         61,     24),	-- Czestochowa-Opole
    ((SELECT id FROM stations WHERE name = "Czestochowa"),  (SELECT id FROM stations WHERE name = "Katowice"),      70,     25),	-- Czestochowa-Katowice
    ((SELECT id FROM stations WHERE name = "Czestochowa"),  (SELECT id FROM stations WHERE name = "Lodz"),          90,     29),	-- Czestochowa-Lodz
    ((SELECT id FROM stations WHERE name = "Czestochowa"),  (SELECT id FROM stations WHERE name = "Kielce"),        104,    34),	-- Czestochowa-Kielce
    ((SELECT id FROM stations WHERE name = "Przemysl"),     (SELECT id FROM stations WHERE name = "Rzeszow"),       70,     25);	-- Przemysl-Rzeszow