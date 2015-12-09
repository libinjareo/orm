drop schema OBJECTOS_ORM;
create schema OBJECTOS_ORM;
use OBJECTOS_ORM;

create table PAIR (

  ID int not null, 
  NAME varchar(120) not null

) engine=InnoDB;

insert into PAIR (ID, NAME)
values (1, "One"), (2, "Two"), (3, "Three");

create table REVISION (

  SEQ int not null auto_increment,
  `DATE` date not null,
  DESCRIPTION varchar(140) not null,
  
  primary key (SEQ)
  
) engine=InnoDB default charset=utf8;

create table APP (

  ID int not null auto_increment,
  
  primary key (ID)

) engine=InnoDB default charset=utf8;