drop if exists database expensetrackerdb;
drop user expensetracker;
drop table et_users

create user expensetracker with password 'password';
create database expensetrackerdb with template=template0 owner=expensetracker;
\connect expensetrackerdb;
alter default privileges grant all on tables to expensetracker;
alter default privileges grant all on sequences to expensetracker;

create table et_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table et_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(50) not null
);

alter table et_categories add constraint cat_users_fk
foreign key  (user_id) REFERENCES et_users(user_id);

create table et_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50) not null,
transaction_date bigint not null,

CONSTRAINT trans_users_fk FOREIGN KEY (user_id) REFERENCES et_users(user_id),
CONSTRAINT trans_category_fk FOREIGN KEY (category_id) REFERENCES et_categories(category_id)
);

create SEQUENCE et_users_seq increment 1 start 1;
create SEQUENCE et_categories_seq increment 1 start 1;
create SEQUENCE et_transactions_seq increment 1 start 1000;