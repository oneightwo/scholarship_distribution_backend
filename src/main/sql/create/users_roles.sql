create table users_roles
(
    user_id bigserial references users (id) not null,
    role_id bigserial references roles (id) not null
);