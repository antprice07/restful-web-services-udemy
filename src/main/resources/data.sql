insert into user_details(id, birth_date, name)
values (10001, current_date(), 'Ranga'),
       (10002, current_date(), 'Anthony'),
       (10003, current_date(), 'Cody');

insert into Post(id, description, user_id)
values (20001, 'I want to learn AWS', 10001),
       (20002, 'I want to learn DevOps', 10001),
       (20003, 'I''m learning spring cloud', 10002),
       (20004, 'I want to learn AWS', 10003),
       (20005, 'I want to learn DevOps', 10003);