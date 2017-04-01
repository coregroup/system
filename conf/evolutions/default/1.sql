# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_course primary key (id))
;

create table module (
  id                        bigint auto_increment not null,
  course_id                 bigint,
  description               varchar(255),
  constraint pk_module primary key (id))
;

create table question (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  level                     varchar(7),
  statement                 varchar(255),
  answer                    varchar(255),
  question_type             varchar(15),
  correction_type           varchar(9),
  available                 tinyint(1) default 0,
  constraint ck_question_level check (level in ('FACIL','MEDIO','DIFICIL')),
  constraint ck_question_question_type check (question_type in ('TEXT','PARAGRAPH_TEXT','MULTIPLE_CHOICE','CHECKBOXES','SCALE','UPLOAD','DATE','TRUE_FALSE')),
  constraint ck_question_correction_type check (correction_type in ('MANUAL','AUTOMATIC')),
  constraint pk_question primary key (id))
;

create table session (
  id                        bigint auto_increment not null,
  course_id                 bigint,
  name                      varchar(255),
  start                     datetime,
  end                       datetime,
  teacher_id                bigint,
  constraint pk_session primary key (id))
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

create table user (
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
  constraint ck_user_state check (state in ('Acre','Alagoas','Amapa','Amazonas','Bahia','Ceara','Distrito_Federal','Espirito_Santo','Goias','Maranhao','Mato_Grosso','Mato_Grosso_do_Sul','Minas_Gerais','Para','Paraiba','Parana','Pernambuco','Piaui','Rio_de_Janeiro','Rio_Grande_do_Norte','Rio_Grande_do_Sul','Rondonia','Roraima','Santa_Catarina','Sao_Paulo','Sergipe','Tocantins')),
  constraint ck_user_gender check (gender in ('MASCULINO','FEMININO','UNANSWERED')),
  constraint pk_user primary key (id))
;


create table module_topic (
  module_id                      bigint not null,
  topic_id                       bigint not null,
  constraint pk_module_topic primary key (module_id, topic_id))
;

create table question_topic (
  question_id                    bigint not null,
  topic_id                       bigint not null,
  constraint pk_question_topic primary key (question_id, topic_id))
;

create table session_student (
  session_id                     bigint not null,
  student_id                     bigint not null,
  constraint pk_session_student primary key (session_id, student_id))
;
alter table module add constraint fk_module_course_1 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_module_course_1 on module (course_id);
alter table session add constraint fk_session_course_2 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_session_course_2 on session (course_id);
alter table session add constraint fk_session_teacher_3 foreign key (teacher_id) references user (id) on delete restrict on update restrict;
create index ix_session_teacher_3 on session (teacher_id);
alter table solution add constraint fk_solution_question_4 foreign key (question_id) references question (id) on delete restrict on update restrict;
create index ix_solution_question_4 on solution (question_id);
alter table solution add constraint fk_solution_user_5 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_solution_user_5 on solution (user_id);



alter table module_topic add constraint fk_module_topic_module_01 foreign key (module_id) references module (id) on delete restrict on update restrict;

alter table module_topic add constraint fk_module_topic_topic_02 foreign key (topic_id) references topic (id) on delete restrict on update restrict;

alter table question_topic add constraint fk_question_topic_question_01 foreign key (question_id) references question (id) on delete restrict on update restrict;

alter table question_topic add constraint fk_question_topic_topic_02 foreign key (topic_id) references topic (id) on delete restrict on update restrict;

alter table session_student add constraint fk_session_student_session_01 foreign key (session_id) references session (id) on delete restrict on update restrict;

alter table session_student add constraint fk_session_student_user_02 foreign key (student_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table course;

drop table module;

drop table module_topic;

drop table question;

drop table question_topic;

drop table session;

drop table session_student;

drop table solution;

drop table topic;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

