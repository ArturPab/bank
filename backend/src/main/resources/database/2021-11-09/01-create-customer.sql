--liquibase formatted sql
--changeset apabjan:1

create table CUSTOMER (
    id bigint not null auto_increment,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    date_of_birth date NOT NULL,
    created datetime(6),
    primary key (id)) engine=InnoDB AUTO_INCREMENT = 1337;

--changeset apabjan:2

insert into CUSTOMER (id, email, password, name, lastname, date_of_birth, created) values (null, 'test@test.com', 'test123', 'John', 'Smith', '2000-09-05', '2021-01-09 16:27:05.068335');
insert into CUSTOMER (id, email, password, name, lastname, date_of_birth, created) values (null, 'test2@test.com', 'test123', 'John2', 'Smith2', '2000-09-05', '2021-01-09 16:27:05.068335');
insert into CUSTOMER (id, email, password, name, lastname, date_of_birth, created) values (null, 'test3@test.com', 'test123', 'John3', 'Smith3', '2000-09-05', '2021-01-09 16:27:05.068335');
