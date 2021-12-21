--liquibase formatted sql
--changeset apabjan:5

create table bank_card (
    id bigint not null auto_increment,
    card_number varchar(16) NOT NULL,
    cvc varchar(3) NOT NULL,
    expiry_date date NOT NULL,
    enabled bit(6) NOT NULL,
    primary key (id)) engine=InnoDB AUTO_INCREMENT = 777777;

--changeset apabjan:6

insert into bank_card (id, card_number, cvc, expiry_date, enabled) values (null, '4000160000000004', '727', '2030-05-01', 1);
insert into bank_card (id, card_number, cvc, expiry_date, enabled) values (null, '4000160000000005', '727', '2030-05-01', 1);
insert into bank_card (id, card_number, cvc, expiry_date, enabled) values (null, '4000160000000006', '727', '2030-05-01', 1);
