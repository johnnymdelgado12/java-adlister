CREATE DATABASE IF NOT EXISTS adlister_db;

USE adlister_db;

CREATE TABLE users (
    id int unsigned not null auto_increment primary key,
    username varchar (50),
    email varchar (50),
    password varchar (30),
    unique (username, email)
);

CREATE TABLE ads (
    id int unsigned not null auto_increment primary key,
    user_id int unsigned not null,
    title varchar (50),
    description varchar (300),
    foreign key (user_id) references users (id)
);



