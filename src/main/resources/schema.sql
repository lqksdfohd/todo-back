drop table if exists todo;
drop table if exists id_table;


create table if not exists todo(
    id integer not null primary key,
    titre varchar,
    description varchar(1000),
    faite boolean
);

create table if not exists id_table(
    id varchar(255) not null primary key,
    next_id bigint not null
);