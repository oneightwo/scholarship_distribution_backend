create table science_directions
(
    id   serial primary key,
    name varchar(255) not null
);

insert into science_directions (name)
values ('Техническое'),
       ('Естественно-научное'),
       ('Социальное');