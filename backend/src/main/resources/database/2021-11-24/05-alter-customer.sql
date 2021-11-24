--liquibase formatted sql
--changeset apabjan:7

ALTER TABLE CUSTOMER add column bank_account_number varchar(26);
ALTER TABLE CUSTOMER add column bank_card_id bigint;
ALTER TABLE CUSTOMER add constraint fkcuba foreign key (bank_card_id) references BANK_CARD(id);

UPDATE CUSTOMER set bank_card_id=777777, bank_account_number='48111198549854985498549854' where id=1337;
UPDATE CUSTOMER set bank_card_id=777778, bank_account_number='48111198549854985498549855' where id=1338;
UPDATE CUSTOMER set bank_card_id=777779, bank_account_number='48111198549854985498549866' where id=1339;