drop table if exists `account`;

create table `account` (
                         `account_id` bigint not null auto_increment,
                         `join_at` datetime(6) not null,
                         `nickname` varchar(20) not null,
                         `email` varchar(100) not null,
                         `profile_img` varchar(255),
                         `uid` varchar(255) not null,
                         primary key (`account_id`)
) engine=InnoDB;

alter table `account`
    add constraint UK_s2a5omeaik0sruawqpvs18qfk unique (`nickname`);

alter table `account`
    add constraint UK_q0uja26qgu1atulenwup9rxyr unique (`email`);

alter table `account`
    add constraint UK_g0r31xiwjpn6bes07nsurpgh9 unique (`uid`);