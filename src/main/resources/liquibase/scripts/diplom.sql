-- liquibase formatted sql

-- changeset irina:1

create table image
(
    id         bigserial PRIMARY KEY,
    file_size  bigint,
    media_type varchar(1000),
    data       bytea
);

create table users
(
    id         bigserial PRIMARY KEY,
    email      varchar(50),
    password   varchar(250),
    first_name varchar(50),
    last_name  varchar(50),
    phone      varchar(50),
    image_id   bigint REFERENCES image(id),
    role       varchar(255),
    city       varchar(255),
    reg_date   timestamp with time zone
);

create table ads
(
    id bigserial PRIMARY KEY,
    title varchar(400),
    description varchar(1000),
    image_id bigint REFERENCES image(id),
    price int,
    author_id bigint REFERENCES users(id)
);

create table ads_comment
(
    id bigserial PRIMARY KEY,
    created_at timestamp,
    text varchar(1000),
    author_id bigint REFERENCES users(id),
    pk_ads bigint REFERENCES ads(id)
);

