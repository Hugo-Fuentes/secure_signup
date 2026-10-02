DROP DATABASE IF EXISTS users;

CREATE DATABASE users;

USE users;

CREATE TABLE USERS(
userId int AUTO_INCREMENT,
name_ varchar(80) NOT NULL,
last_name varchar(80) NOT NULL,
email varchar(150) UNIQUE NOT NULL,
password_ varchar(150) NOT NULL,
PRIMARY KEY(userId)
);