insert into usr (id, username, password, active)
values (1, 'admin', '123', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');

insert into usr (id, username, password, active, email)
values (2, 'alex', '123', true, 'alex@gmail.com');

insert into user_role (user_id, roles)
values (2, 'USER');

insert into usr (id, username, password, active, email)
values (3, 'ion', '123', true, 'ion@gmail.com');

insert into user_role (user_id, roles)
values (3, 'USER');