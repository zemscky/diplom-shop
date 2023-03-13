-- liquibase formatted sql

-- changeset irina:1
create table ads
(
    id bigserial,
    title varchar(400),
    description varchar(1000),
    image_id bigint,
    price int,
    author_id bigint
);

create table ads_comment
(
    id bigserial,
    created_at timestamp,
    "text" varchar(1000),
    author_id bigint,
    pk_ads bigint
);

create table image
(
    id         bigserial,
    file_size  bigint,
    media_type varchar(1000),
    data       bytea,
    ads_id     bigint,
    user_id    bigint
);

create table users
(
    id         bigserial,
    email      varchar(50),
    password   varchar(50),
    first_name varchar(50),
    last_name  varchar(50),
    phone      varchar(50),
    image_id   bigint,
    role       varchar(255),
    city       varchar(255),
    reg_date   timestamp with time zone
);
