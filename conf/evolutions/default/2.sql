# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Question (
  id                        bigint auto_increment not null,
  enunciation               varchar(2000),
  topic						varchar(255),
  solution					varchar(255),
  dica1						varchar(255),
  dica2						varchar(255),
  dica3						varchar(255),
  dica4						varchar(255),
  dica5						varchar(255),
  constraint pk_question primary key (id))
;

create table Logexp (
  id                        bigint auto_increment not null,
  idStudent	                bigint,
  idTurma					varchar(255),
  idQuestion				bigint,
  topic						varchar(255),
  solution					varchar(255),
  idDica					varchar(255),
  desistiu                  tinyint(1) default 0,
  horario                   datetime,
  correto					tinyint(1) default 0,
  model						varchar(255),
  constraint pk_logexp primary key (id))
;



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table Question;

drop table Logexp;

SET FOREIGN_KEY_CHECKS=1;

