CREATE TABLE [IF NOT EXISTS] USER (
 user_id serial primary key,
 username varchar (256),
 password varchar (256) not null,
 email varchar (256) unique not null,
 created_on timestamp not null
);