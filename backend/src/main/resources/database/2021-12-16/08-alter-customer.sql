--liquibase formatted sql
--changeset apabjan:9

alter table CUSTOMER add column balance DECIMAL(12,2);
update CUSTOMER set balance=100000;