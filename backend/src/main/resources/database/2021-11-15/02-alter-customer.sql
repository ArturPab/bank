--liquibase formatted sql
--changeset apabjan:3

ALTER TABLE customer add column enabled bit(6)