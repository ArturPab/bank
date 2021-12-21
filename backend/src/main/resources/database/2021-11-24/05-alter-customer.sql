--liquibase formatted sql
--changeset apabjan:7

ALTER TABLE customer add column bank_account_number varchar(26);
ALTER TABLE customer add column bank_card_id bigint;
ALTER TABLE customer add constraint fkcuba foreign key (bank_card_id) references bank_card(id);

UPDATE customer set bank_card_id=777777, bank_account_number='48111198549854985498549854' where id=1337;
UPDATE customer set bank_card_id=777778, bank_account_number='48111198549854985498549855' where id=1338;
UPDATE customer set bank_card_id=777779, bank_account_number='48111198549854985498549866' where id=1339;