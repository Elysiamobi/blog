CREATE DATABASE yc1_blog ;
USE yc1_blog;
create table categories
(
    category_id int auto_increment
        primary key,
    name        varchar(255)                        null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table roles
(
    role_id   int auto_increment
        primary key,
    role_name varchar(255) null
);

create table users
(
    user_id    int auto_increment
        primary key,
    username   varchar(255)                        null,
    password   varchar(255)                        not null,
    email      varchar(255)                        null,
    role_id    int                                 null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    role       varbinary(255)                      null,
    constraint FKp56c1712k691lhsyewcssf40f
        foreign key (role_id) references roles (role_id)
);



create table posts
(
    post_id     int auto_increment
        primary key,
    title       varchar(255)                        not null,
    content     longtext                            null,
    author_id   int                                 null,
    category_id int                                 null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint FK6xvn0811tkyo3nfjk2xvqx6ns
        foreign key (author_id) references users (user_id),
    constraint fk_category_id
        foreign key (category_id) references categories (category_id)
            on delete set null
);

create table comments
(
    comment_id int auto_increment
        primary key,
    post_id    int                                 null,
    user_id    int                                 null,
    content    longtext                            null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint comments_ibfk_1
        foreign key (post_id) references posts (post_id)
            on delete cascade,
    constraint comments_ibfk_2
        foreign key (user_id) references users (user_id)
            on delete cascade
);

create index idx_post_id
    on comments (post_id);

create index user_id
    on comments (user_id);

create index idx_title
    on posts (title);

create index idx_username
    on users (username);

INSERT INTO categories (name) VALUES ('Technology'), ('Lifestyle'), ('Education'), ('Travel');

-- 插入用户和角色数据
INSERT INTO roles (role_name, role_id) VALUES ('USER',1), ('ADMIN',2);
INSERT INTO users (username, password, role_id)
VALUES ('admin', 'admin', 2);