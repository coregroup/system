# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                        bigint auto_increment not null,
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
  state                     varchar(19),
  institution               varchar(255),
  teaching_area             varchar(255),
  constraint ck_user_state check (state in ('Acre','Alagoas','Amapa','Amazonas','Bahia','Ceara','Distrito_Federal','Espirito_Santo','Goias','Maranhao','Mato_Grosso','Mato_Grosso_do_Sul','Minas_Gerais','Para','Paraiba','Parana','Pernambuco','Piaui','Rio_de_Janeiro','Rio_Grande_do_Norte','Rio_Grande_do_Sul','Rondonia','Roraima','Santa_Catarina','Sao_Paulo','Sergipe','Tocantins')),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table person;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

