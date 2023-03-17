-- liquibase formatted sql

-- changeset irina:1
create table users
(
    id         bigserial primary key,
    email      varchar(50),
    password   varchar(250),
    first_name varchar(50),
    last_name  varchar(50),
    phone      varchar(50),
    image_id   bigint,
    role       varchar(255),
    city       varchar(255),
    reg_date   timestamp with time zone
);

create table image
(
    id         bigserial primary key,
    file_size  bigint,
    media_type varchar(1000),
    data       bytea
);

create table ads
(
    id bigserial primary key,
    title varchar(400),
    description varchar(1000),
    image_id bigint,
    price int,
    author_id bigint
);

create table ads_comment
(
    id bigserial primary key,
    created_at timestamp,
    "text" varchar(1000),
    author_id bigint,
    pk_ads bigint
);



