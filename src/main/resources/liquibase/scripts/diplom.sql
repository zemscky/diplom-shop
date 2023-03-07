-- liquibase formatted sql

-- changeset irina:1
create table ads
(
    id bigserial,
    title varchar(400),
    description text,
    image text,
    price int,
    author_id bigint
);

create table ads_comment
(
    id bigserial,
    created_at timestamp,
    "text" text,
    author_id bigint,
    pk_ads bigint
);

create table image
(
    id         bigserial,
    file_path  text,
    file_size  bigint,
    media_type text,
    data       bytea,
    ads_id     bigint
);

create table "user"
(
    id        bigserial,
    email     varchar(50),
    password  varchar(50),
    first_name varchar(50),
    last_name  varchar(50),
    phone     varchar(50),
    image     text,
    role      varchar(255)
);
