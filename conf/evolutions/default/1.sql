# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create table user (
  dtype                     varchar(10) not null,
  id                        bigint auto_increment not null,
  fullname                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  date_of_birth             datetime,
  institution               varchar(255),
  teaching_area             varchar(255),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table person;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

