--grant usage on schema public to public;
--grant create on schema public to public;

--create sequence hibernate_sequence start 1 increment 1;

create table usr
(
    id              bigserial primary key,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null
);

create table message
(
    id       bigserial primary key,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(2048) not null,
    user_id  bigint not null
);

create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
);

alter table if exists message
    add constraint message_user_fk
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr;