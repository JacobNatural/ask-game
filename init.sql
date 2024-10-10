CREATE TABLE IF NOT EXISTS players
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS statistics
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    player_id INTEGER,
    stat_time DATETIME NOT NULL,
    score     INTEGER  NOT NULL,
    FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS quests
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    difficulty_level VARCHAR(1)   NOT NULL,
    category         VARCHAR(50)  NOT NULL,
    question         VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS answers
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    quest_id INTEGER      NOT NULL,
    answer   VARCHAR(200) not null,
    confirm  BOOLEAN      NOT NULL,
    FOREIGN KEY (quest_id) REFERENCES quests (id) ON DELETE CASCADE
);

insert into players(login)
values ('Jony'),
       ('Jakub'),
       ('Darek'),
       ('Michael'),
       ('Stefan'),
       ('Tadek'),
       ('Moon'),
       ('Tower');

insert into statistics(player_id,stat_time, score)
values(1, '2024-08-28 16:00:00', 140),
(1, '2024-08-28 14:30:00', 60),
(2, '2024-07-20 12:30:00', 110),
(2, '2024-08-04 13:30:00', 120),
(2, '2024-08-01 10:20:00', 50),
(3, '2024-08-28 18:35:00', 80),
(4, '2024-02-24 17:00:00', 40),
(4, '2024-03-20 19:38:00', 140),
(5, '2024-01-20 22:30:00', 110),
(5, '2024-02-24 16:00:00', 70),
(5, '2024-03-13 11:30:00', 95),
(6, '2024-04-01 08:00:20', 60),
(7, '2024-04-04 09:10:00', 110),
(7, '2024-04-20 15:30:00', 100),
(8, '2024-09-01 13:30:00', 65),
(8, '2024-09-10 13:55:00', 80);





insert into quests(difficulty_level, category, question)
values ('B', 'music', 'Who composed "Carmen"?'),
       ('A', 'culture',
        'In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?'),
       ('B', 'geography', 'In which city in Poland is the famous castle that is the largest in Europe by area?'),
       ('A', 'literature', 'Who is the author of "Crime and Punishment"?'),
       ('C', 'art', 'Who is the author of the sculpture "David"?'),
       ('A', 'geography', 'What is the name of the highest peak in the Polish Tatras?'),
       ('A', 'geography',
        'In which Polish city is the famous market hall, one of the largest of its kind in Europe, located?'),
       ('B', 'history', 'Who was the king of England during the Hundred Years'' War?'),
       ('B', 'music', 'Who composed the "Great Water Chorus"?'),
       ('C', 'science', 'What is the name of the highest mountain on Earth?'),
       ('B', 'geography',
        'What is the name of the oldest mine in Poland, which is one of the oldest active mining operations in the world?'),
       ('C', 'music', 'Which Polish city is considered the capital of jazz?'),
       ('B', 'music', 'In which Polish city is the largest hip-hop culture festival in Europe held?'),
       ('B', 'history', 'Car manufactured in Poland?'),
       ('A', 'biology', 'Popular color of a squirrel?'),
       ('C', 'science', 'Which chemical element has the symbol "Na"?'),
       ('B', 'film', 'Which Polish city is famous for hosting an annual film festival?'),
       ('C', 'science', 'What is the name of the largest ocean on Earth?'),
       ('B', 'history', 'Who was the first Roman emperor?'),
       ('C', 'science', 'Which chemical element has the symbol "Si"?'),
       ('C', 'geography', 'What is the highest mountain in Poland?'),
       ('B', 'sport', 'Who is currently considered the best football player in the world?'),
       ('A', 'sport', 'Who is currently considered the best tennis player in the world?'),
       ('A', 'art', 'Who is the author of "The Dark Side of the Moon"?'),
       ('C', 'culture', 'Which city in Poland is considered the cultural capital of the country?'),
       ('A', 'geography',
        'In which city in Poland is the famous cathedral, one of the largest churches in Europe, located?'),
       ('B', 'culture', 'Which Polish city is considered the youth and student life capital?'),
       ('B', 'film', 'Which Polish city is famous for the "Mozg" horror film festival?'),
       ('A', 'sport', 'Which football team won the World Cup in 2018?'),
       ('C', 'geography', 'In which Polish city is the famous salt mine located?'),
       ('B', 'culture', 'Which Polish city is considered the capital of cabaret?'),
       ('B', 'history', 'Who was the first president of the United States?'),
       ('A', 'music', 'Which Polish city is famous for the "Pol''and''Rock" music festival?'),
       ('A', 'geography', 'Capital of Poland?'),
       ('B', 'history', 'In what year did the Battle of Grunwald take place?'),
       ('A', 'sport', 'Who won the gold medal in ski jumping at the 2018 Winter Olympics?'),
       ('A', 'sport', 'Which sport is known as the "Queen of Sports"?'),
       ('C', 'film', 'Which Polish city is famous for the "OFF Camera" independent film festival?'),
       ('C', 'music', 'Which Polish city is famous for the "Open''er" music festival?'),
       ('B', 'geography', 'What is the name of the longest river in Poland?'),
       ('C', 'geography',
        'In which city in Poland is the famous monument of the Savior, one of the largest statues of Jesus in the world, located?'),
       ('C', 'geography', 'What is the oldest national park in Poland?'),
       ('B', 'geography', 'What is the name of the largest lake in Poland?'),
       ('B', 'history', 'What is the oldest university in Poland?'),
       ('B', 'music', 'In which Polish city is one of the oldest opera theaters in Europe located?'),
       ('C', 'science', 'What is the name of the outermost layer of the Earth''s atmosphere?'),
       ('B', 'history', 'In which Polish city is the oldest university located?'),
       ('C', 'science', 'What is the name of Jupiter''s largest moon?'),
       ('A', 'music', 'Which city in Poland is known for the "Woodstock" music festival?'),
       ('C', 'geography', 'In which Polish city is the annual "World Knowledge Championship" held?'),
       ('A', 'culture', 'Who wrote "Pan Tadeusz"?'),
       ('C', 'geography', 'What is the largest sand dune in Poland?'),
       ('A', 'science', 'Popular phone in the USA?');



insert into answers(quest_id, answer, confirm)
values (1, 'Wolfgang Amadeus Mozart', false),
       (1, 'Johann Strauss II', false),
       (1, 'Georges Bizet', true),

       (2, 'Warsaw', true),
       (2, 'Krakow', false),
       (2, 'Gdansk', false),

       (3, 'Ksiaz', false),
       (3, 'Czocha', false),
       (3, 'Malbork', true),

       (4, 'Leo Tolstoy', false),
       (4, 'Fyodor Dostoevsky', true),
       (4, 'Ivan Turgenev', false),

       (5, 'Michelangelo', true),
       (5, 'Leonardo da Vinci', false),
       (5, 'Pablo Picasso', false),

       (6, 'Kasprowy Wierch', false),
       (6, 'Rysy', true),
       (6, 'Swinica', false),

       (7, 'Katowice', false),
       (7, 'Poznan', true),
       (7, 'Lodz', false),

       (8, 'Edward III', false),
       (8, 'Richard III', false),
       (8, 'Henry V', true),

       (9, 'Fryderyk Chopin', true),
       (9, 'Ludwig van Beethoven', false),
       (9, 'Johann Sebastian Bach', false),

       (10, 'Mount Everest', true),
       (10, 'K2', false),
       (10, 'Kangchenjunga', false),

       (11, 'Wieliczka Mine', false),
       (11, 'Tarnowskie Gory Mine', false),
       (11, 'Bochnia Mine', true),

       (12, 'Warsaw', false),
       (12, 'Gdansk', false),
       (12, 'Krakow', true),

       (13, 'Warsaw', false),
       (13, 'Krakow', false),
       (13, 'Katowice', true),

       (14, 'Mercedes', false),
       (14, 'Fiat', true),
       (14, 'Ford', false),

       (15, 'Red', true),
       (15, 'White', false),
       (15, 'Black', false),

       (16, 'Sodium', true),
       (16, 'Potassium', false),
       (16, 'Magnesium', false),

       (17, 'Lodz', false),
       (17, 'Szczecin', false),
       (17, 'Gdynia', true),

       (18, 'Pacific', true),
       (18, 'Atlantic', false),
       (18, 'Indian', false),

       (19, 'Julius Caesar', false),
       (19, 'Nero', false),
       (19, 'Augustus', true),

       (20, 'Selenium', false),
       (20, 'Sodium', false),
       (20, 'Silicon', true),

       (21, 'Sniezka', false),
       (21, 'Kasprowy Wierch', false),
       (21, 'Rysy', true),

       (22, 'Cristiano Ronaldo', false),
       (22, 'Lionel Messi', true),
       (22, 'Neymar', false),

       (23, 'Rafael Nadal', false),
       (23, 'Novak Djokovic', true),
       (23, 'Roger Federer', false),

       (24, 'Pink Floyd', true),
       (24, 'The Beatles', false),
       (24, 'Led Zeppelin', false),

       (25, 'Krakow', true),
       (25, 'Gdansk', false),
       (25, 'Poznan', false),

       (26, 'Warsaw', false),
       (26, 'Gniezno', false),
       (26, 'Wroclaw', true),

       (27, 'Poznan', false),
       (27, 'Wroclaw', true),
       (27, 'Krakow', false),

       (28, 'Poznan', false),
       (28, 'Gdansk', false),
       (28, 'Bydgoszcz', true),

       (29, 'Croatia', false),
       (29, 'France', true),
       (29, 'Argentina', false),

       (30, 'Wieliczka', true),
       (30, 'Bochnia', false),
       (30, 'Inowroclaw', false),

       (31, 'Warsaw', false),
       (31, 'Krakow', true),
       (31, 'Wroclaw', false),

       (32, 'Thomas Jefferson', false),
       (32, 'Abraham Lincoln', false),
       (32, 'George Washington', true),

       (33, 'Kostrzyn nad Odra', true),
       (33, 'Sopot', false),
       (33, 'Gdynia', false),

       (34, 'Warsaw', true),
       (34, 'Krakow', false),
       (34, 'Wroclaw', false),

       (35, '1410', true),
       (35, '1569', false),
       (35, '1655', false),

       (36, 'Dawid Kubacki', false),
       (36, 'Andreas Wellinger', false),
       (36, 'Kamil Stoch', true),

       (37, 'Athletics', true),
       (37, 'Football', false),
       (37, 'Tennis', false),

       (38, 'Krakow', true),
       (38, 'Warsaw', false),
       (38, 'Lublin', false),

       (39, 'Szczecin', false),
       (39, 'Gdynia', true),
       (39, 'Bydgoszcz', false),

       (40, 'Vistula', true),
       (40, 'Oder', false),
       (40, 'Warta', false),

       (41, 'Warsaw', false),
       (41, 'Swiebodzin', true),
       (41, 'Czestochowa', false),

       (42, 'Tatrzanski', false),
       (42, 'Biebrzanski', false),
       (42, 'Babiogorski', true),

       (43, 'Mamry', false),
       (43, 'Lebsko', false),
       (43, 'Sniardwy', true),

       (44, 'Jagiellonian University', false),
       (44, 'University of Warsaw', false),
       (44, 'University of Wroclaw', true),

       (45, 'Poznan', false),
       (45, 'Gdansk', false),
       (45, 'Wroclaw', true),

       (46, 'Stratosphere', false),
       (46, 'Mesosphere', false),
       (46, 'Exosphere', true),

       (47, 'Warsaw', false),
       (47, 'Krakow', true),
       (47, 'Wroclaw', false),

       (48, 'Europa', false),
       (48, 'Ganymede', true),
       (48, 'Callisto', false),

       (49, 'Sopot', false),
       (49, 'Kostrzyn nad Odra', true),
       (49, 'Krakow', false),

       (50, 'Poznan', false),
       (50, 'Wroclaw', true),
       (50, 'Krakow', false),

       (51, 'Juliusz Slowacki', false),
       (51, 'Adam Mickiewicz', true),
       (51, 'Henryk Sienkiewicz', false),

       (52, 'Lacka Dune', false),
       (52, 'Czolpinska Dune', false),
       (52, 'Leba Dune', true),

       (53, 'Nokia', false),
       (53, 'iPhone', true),
       (53, 'Android', false);





