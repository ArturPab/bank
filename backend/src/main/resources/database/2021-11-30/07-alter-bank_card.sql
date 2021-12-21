--liquibase formatted sql
--changeset apabjan:9

alter table bank_card add column pin varchar(4);
update bank_card set pin='1111';