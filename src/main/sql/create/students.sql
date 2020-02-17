create table students
(
    id                   bigserial primary key,
    surname              varchar(255)                                not null,
    name                 varchar(255)                                not null,
    patronymic           varchar(255),
    university_id        smallint references universities (id)       not null,
    faculty              varchar(255)                                not null,
    course_id            smallint references courses (id)            not null,
    email                varchar(255),
    phone                varchar(255),
    science_direction_id smallint references science_directions (id) not null,
    topic                varchar(255)                                not null,
    c1                   smallint                                    not null,
    c2                   smallint                                    not null,
    c3                   smallint                                    not null,
    c4                   smallint                                    not null,
    c5                   smallint                                    not null,
    c6                   smallint                                    not null,
    c7                   smallint                                    not null,
    c8                   smallint                                    not null,
    c9                   smallint                                    not null,
    c10                  smallint                                    not null,
    c11                  smallint                                    not null,
    c12                  smallint                                    not null,
    c13                  smallint                                    not null,
    c14                  smallint                                    not null,
    c15                  smallint                                    not null,
    rating               smallint                                    not null,
    data_registration    timestamp                                   not null,
    is_valid             boolean                                     not null
);

drop table students;