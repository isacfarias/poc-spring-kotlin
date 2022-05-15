CREATE TABLE [IF NOT EXISTS] USER (
 user_id serial primary key,
 username varchar (256),
 email varchar (256) unique not null,
 created_on timestamp not null
);