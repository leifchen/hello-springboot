create database if not exists db_master;
use db_master;
create table if not exists test
(
    id   int,
    name varchar(10)
);
insert into test(id, name) values(1, 'master');

create database if not exists db_slave;
use db_slave;
create table if not exists test
(
    id   int,
    name varchar(10)
);
insert into test(id, name) values(1, 'slave');
