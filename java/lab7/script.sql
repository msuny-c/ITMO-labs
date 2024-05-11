CREATE TYPE MOOD AS ENUM ('SORROW', 'LONGING', 'APATHY', 'CALM', 'FRENZY');
CREATE TYPE WEAPON_TYPE AS ENUM ('RIFLE', 'KNIFE', 'MACHINE_GUN', 'BAT');
CREATE CAST (varchar AS MOOD) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS WEAPON_TYPE) WITH INOUT AS IMPLICIT;

CREATE TABLE coordinates (
    id SERIAL PRIMARY KEY NOT NULL,
    x FLOAT NOT NULL,
    y BIGINT NOT NULL
);

CREATE TABLE car (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    cool BOOL NOT NULL
);

CREATE TABLE users (
    name VARCHAR PRIMARY KEY NOT NULL,
    password VARCHAR NOT NULL,
    salt VARCHAR(128) NOT NULL
);

CREATE TABLE human_being (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    creationDate timestamp NOT NULL default current_timestamp,
    mood MOOD,
    weapon_type WEAPON_TYPE,
    realHero BOOL NOT NULL,
    hasToothPick BOOL,
    impactSpeed BIGINT,
    coordinates_id SERIAL REFERENCES coordinates(id),
    car_id SERIAL REFERENCES car(id),
    user_name VARCHAR REFERENCES users(name)
);