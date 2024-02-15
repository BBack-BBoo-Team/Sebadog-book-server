drop table if exists `book`;

create table `book` (
                      `book_id` bigint not null auto_increment,
                      `created_dt` datetime(6),
                      `finish_dt` datetime(6),
                      `updated_dt` datetime(6),
                      `author` varchar(255) not null,
                      `created_by` varchar(255) not null,
                      `img` varchar(255) not null,
                      `publisher` varchar(255) not null,
                      `title` varchar(255) not null,
                      `status` enum ('BEFORE_PROGRESS','IN_PROGRESS','COMPLETED') not null,
                      primary key (`book_id`)
) engine=InnoDB;