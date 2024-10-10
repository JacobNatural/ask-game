CREATE TABLE IF NOT EXISTS quests
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    difficulty_level VARCHAR(1)   NOT NULL,
    category         VARCHAR(50)  NOT NULL,
    question         VARCHAR(300) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS answers
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    quest_id INTEGER      NOT NULL,
    answer   VARCHAR(200) not null,
    confirm  BOOLEAN      NOT NULL,
    FOREIGN KEY (quest_id) REFERENCES quests (id) ON DELETE CASCADE
    );

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

insert into players(login)
values ('Jony'),
       ('Jakub'),
       ('Darek');

insert into statistics(player_id,stat_time, score)
values(1, '2024-08-28 16:00:00', 140),
      (1, '2024-08-28 14:30:00', 60),
      (2, '2024-07-20 12:30:00', 110),
      (2, '2024-08-04 13:30:00', 120),
      (2, '2024-08-01 10:20:00', 50),
      (3, '2024-08-10 18:35:00', 120);

insert into quests(difficulty_level, category, question)
values ('B', 'music', 'Who composed "Carmen"?'),
       ('A', 'culture',
        'In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?'),
       ('B', 'geography', 'In which city in Poland is the famous castle that is the largest in Europe by area?');

insert into answers(quest_id, answer, confirm)
values (1, 'Wolfgang Amadeus Mozart', false),
       (1, 'Johann Strauss II', false),
       (1, 'Georges Bizet', true),

       (2, 'Warsaw', true),
       (2, 'Krakow', false),
       (2, 'Gdansk', false),

       (3, 'Ksiaz', false),
       (3, 'Czocha', false),
       (3, 'Malbork', true);