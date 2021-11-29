--liquibase formatted sql
--changeset apabjan:8

alter table customer add column role varchar(10);

UPDATE CUSTOMER set role='USER';
