--changeset Scrimlab:BTS-42-local

CREATE TABLE PrizeList (
    player TEXT NOT NULL,
    tournament_name TEXT NOT NULL,
    result INT,
    tournament_date DATE,
    CONSTRAINT pk_prize_list PRIMARY KEY (player, tournament_name),
    CONSTRAINT fk_player FOREIGN KEY (player) REFERENCES Player(email) ON UPDATE CASCADE ON DELETE SET NULL
)