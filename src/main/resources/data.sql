CREATE TABLE model
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255)     NOT NULL UNIQUE,
    description VARCHAR(255)     NOT NULL,
    capacity    DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO MODEL(CAPACITY, DESCRIPTION, NAME)
values (100, 'Light Weight', 'LIGHT_WEIGHT'),
       (250, 'Middle Weight', 'MIDDLE_WEIGHT'),
       (350, 'Cruiser Weight', 'CRUISER_WEIGHT'),
       (500, 'Heavy Weight', 'HEAVY_WEIGHT');


CREATE TABLE state
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO STATE(DESCRIPTION, NAME)
values ('Idle', 'IDLE'),
       ('Loading', 'LOADING'),
       ('Loaded', 'LOADED'),
       ('Delivering', 'DELIVERING'),
       ('Delivered', 'DELIVERED'),
       ('Returning', 'RETURNING');

