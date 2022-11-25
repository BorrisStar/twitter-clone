grant usage on schema public to public;
grant create on schema public to public;

create table usr
(
    id              bigserial primary key,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255),
    username        varchar(255)
);

create table message
(
    id       bigserial primary key,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(255),
    user_id  bigint
        constraint usr_id references usr
);

create table user_role
(
    user_id bigint not null
        constraint usr_id references usr,
    roles   varchar(255)
);