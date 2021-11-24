--liquibase formatted sql
--changeset apabjan:3

ALTER TABLE CUSTOMER add column enabled bit(6)