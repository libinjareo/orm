drop schema OBJECTOS_ORM;
create schema OBJECTOS_ORM;
use OBJECTOS_ORM;

create table PAIR (

  ID int not null, 
  NAME varchar(120) not null

) engine=InnoDB;

insert into PAIR (ID, NAME)
values (1, "One"), (2, "Two"), (3, "Three");