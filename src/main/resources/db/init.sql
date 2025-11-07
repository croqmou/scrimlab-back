DROP TABLE IF EXISTS Friend CASCADE;
DROP TABLE IF EXISTS Ban CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS Team CASCADE;
DROP TABLE IF EXISTS Scrim CASCADE;
DROP TABLE IF EXISTS Mode CASCADE;
DROP TABLE IF EXISTS Title CASCADE;
DROP TABLE IF EXISTS PlayerTitle CASCADE;



CREATE TABLE Player(
email TEXT NOT NULL PRIMARY KEY,
username VARCHAR(20) UNIQUE NOT NULL,
pwd TEXT NOT NULL,
pp TEXT,
admin BOOL DEFAULT false,
player_goals INT default 0,
player_wins INT default 0,
player_loses INT default 0,
ranking_points_1s INT DEFAULT 0,
ranking_points_2s INT DEFAULT 0,
ranking_points_3s INT DEFAULT 0);




CREATE TABLE Friend(
playerEntity TEXT NOT NULL,
friend TEXT NOT NULL,
CONSTRAINT pk_friend PRIMARY KEY (playerEntity, friend),
CONSTRAINT fk_player FOREIGN KEY (playerEntity) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_friend FOREIGN KEY (playerEntity) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL);


CREATE TABLE Ban(
playerEntity TEXT NOT NULL PRIMARY KEY,
time DATE NOT NULL,
CONSTRAINT fk_ban FOREIGN KEY (playerEntity) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL
);



CREATE TABLE Team(
team_name VARCHAR(26) NOT NULL PRIMARY KEY,
team_logo TEXT,
team_banner TEXT,
team_description VARCHAR(50),
captain TEXT,
player_two TEXT,
player_three TEXT,
sub TEXT,
second_sub TEXT,
coach TEXT,
manager TEXT,
ranking_points INT DEFAULT 0,
team_goals INT default 0,
team_wins INT default 0,
team_loses INT default 0,
CONSTRAINT fk_p1 FOREIGN KEY (captain) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_p2 FOREIGN KEY (player_two) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_p3 FOREIGN KEY (player_three) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_sub FOREIGN KEY (sub) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_second_sub FOREIGN KEY (second_sub) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_coach FOREIGN KEY (coach) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_manager FOREIGN KEY (manager) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL);


CREATE TABLE Mode(
    mode_name TEXT PRIMARY KEY NOT NULL
);

CREATE TABLE Scrim(
scrim_id SERIAL PRIMARY KEY,
team_one VARCHAR(26) NOT NULL,
team_two VARCHAR(26),
mode TEXT,
bo INT,
elo INT,
match_date DATE NOT NULL,
hour TIME NOT NULL,
duration TIME,
description TEXT,
CONSTRAINT fk_mode FOREIGN KEY (mode) REFERENCES Mode(mode_name) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_t1 FOREIGN KEY (team_one) REFERENCES Team(team_name) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_t2 FOREIGN KEY (team_two) REFERENCES Team(team_name) ON UPDATE CASCADE ON DELETE SET NULL);


CREATE TABLE Title(
title_name TEXT PRIMARY KEY NOT NULL,
title_color TEXT NOT NULL,
is_neon BOOL DEFAULT false
);

CREATE TABLE PlayerTitle (
playerEntity TEXT NOT NULL,
title TEXT NOT NULL,
use BOOL NOT NULL DEFAULT false,
CONSTRAINT pk_playerTitle PRIMARY KEY (playerEntity, title),
CONSTRAINT fk_t2 FOREIGN KEY (playerEntity) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL
);



--INSERT INTO Team VALUES('la famille coin coin', 'coin coin', null, null, null, null, null, null);

--INSERT INTO Scrim VALUES('la famille coin coin', null, 2, 1700, '2025-01-05', '14:0:0', null, 'Venez vous traine pour les RLCS');

