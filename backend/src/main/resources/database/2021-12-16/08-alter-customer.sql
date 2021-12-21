--liquibase formatted sql
--changeset apabjan:9

alter table customer add column balance DECIMAL(12,2);
update customer set balance=100000;