--liquibase formatted sql
--changeset parvesh:player_table
create table if not exists players (
    id bigserial not null,
    playerid varchar not null,
    name varchar not null,
    runs varchar not null,
    wickets varchar not null,
    catches varchar not null,
    primary key (id),
    UNIQUE (playerid)
);
-- rollback drop table test_table;

