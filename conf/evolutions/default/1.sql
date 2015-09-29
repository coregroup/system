# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table question (
  id                        bigint auto_increment not null,
  level                     varchar(7),
  statement                 varchar(255),
  answer                    varchar(255),
  question_type             varchar(15),
  correction_type           varchar(9),
  available                 tinyint(1) default 0,
  constraint ck_question_level check (level in ('FACIL','MEDIO','DIFICIL')),
  constraint ck_question_question_type check (question_type in ('TEXT','PARAGRAPH_TEXT','MULTIPLE_CHOICE','CHECKBOXES','SCALE','UPDATE','DATE','TRUE_FALSE')),
  constraint ck_question_correction_type check (correction_type in ('MANUAL','AUTOMATIC')),
  constraint pk_question primary key (id))
;

create table solution (
  id                        bigint auto_increment not null,
  question_id               bigint,
  user_id                   bigint,
  date                      datetime,
  answer                    varchar(255),
  evaluation                tinyint(1) default 0,
  constraint pk_solution primary key (id))
;

create table topic (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_topic primary key (id))
;

create table User (
  dtype                     varchar(10) not null,
  id                        bigint auto_increment not null,
  fullname                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  date_of_birth             datetime,
  active                    tinyint(1) default 0,
  last_access               datetime,
  state                     varchar(19),
  gender                    varchar(10),
  institution               varchar(255),
  teaching_area             varchar(255),
  constraint ck_User_state check (state in ('Acre','Alagoas','Amapa','Amazonas','Bahia','Ceara','Distrito_Federal','Espirito_Santo','Goias','Maranhao','Mato_Grosso','Mato_Grosso_do_Sul','Minas_Gerais','Para','Paraiba','Parana','Pernambuco','Piaui','Rio_de_Janeiro','Rio_Grande_do_Norte','Rio_Grande_do_Sul','Rondonia','Roraima','Santa_Catarina','Sao_Paulo','Sergipe','Tocantins')),
  constraint ck_User_gender check (gender in ('MASCULINO','FEMININO','UNANSWERED')),
  constraint pk_User primary key (id))
;


create table question_topic (
  question_id                    bigint not null,
  topic_id                       bigint not null,
  constraint pk_question_topic primary key (question_id, topic_id))
;
alter table solution add constraint fk_solution_question_1 foreign key (question_id) references question (id) on delete restrict on update restrict;
create index ix_solution_question_1 on solution (question_id);
alter table solution add constraint fk_solution_user_2 foreign key (user_id) references User (id) on delete restrict on update restrict;
create index ix_solution_user_2 on solution (user_id);



alter table question_topic add constraint fk_question_topic_question_01 foreign key (question_id) references question (id) on delete restrict on update restrict;

alter table question_topic add constraint fk_question_topic_topic_02 foreign key (topic_id) references topic (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table question;

drop table question_topic;

drop table solution;

drop table topic;

drop table User;

SET FOREIGN_KEY_CHECKS=1;

