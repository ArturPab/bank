--liquibase formatted sql
--changeset apabjan:9

alter table BANK_CARD add column pin varchar(4);
update BANK_CARD set pin='1111';