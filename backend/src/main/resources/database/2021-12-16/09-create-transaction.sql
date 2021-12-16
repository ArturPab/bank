--liquibase formatted sql
--changeset apabjan:10

create table TRANSACTION (
                          id bigint not null auto_increment,
                          title varchar(100) NOT NULL,
                          recipient varchar(90) NOT NULL,
                          recipient_account_number varchar(26) NOT NULL,
                          amount decimal(12,2) NOT NULL,
                          date datetime(6) NOT NULL,
                          customer_id bigint not null,
                          primary key (id)) engine=InnoDB AUTO_INCREMENT = 10000;
