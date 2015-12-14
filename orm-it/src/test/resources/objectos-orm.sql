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

create table EMPLOYEE (

  EMP_NO int not null, 
  BIRTH_DATE date not null,
  FIRST_NAME varchar(14) not null,
  LAST_NAME varchar(16) not null,
  HIRE_DATE date not null,
  
  primary key(EMP_NO)

) engine=InnoDB;

insert into 
EMPLOYEE (EMP_NO, BIRTH_DATE, FIRST_NAME, LAST_NAME, HIRE_DATE)
values 
(1, '1970-01-01', 'Homer', 'Simpson', '2010-01-01'), 
(2, '1975-06-25', 'Marge', 'Simpson', '2011-05-03'), 
(3, '1980-09-05', 'Moe', 'Szyslak', '2012-02-01');

create table SALARY (

  EMP_NO int not null, 
  SALARY int not null,
  FROM_DATE date not null,
  TO_DATE date not null,
  
  foreign key (EMP_NO) references EMPLOYEE (EMP_NO) on delete cascade,
  primary key (EMP_NO, FROM_DATE)

) engine=InnoDB;

insert into 
SALARY (EMP_NO, SALARY, FROM_DATE, TO_DATE)
values 
(1, 1000, '2010-01-01', '2010-12-31'), 
(1, 2500, '2011-01-01', '2011-12-31'),
(1, 3000, '2012-01-01', '2012-12-31'),
(2, 1000, '2010-01-01', '2010-12-31'), 
(2, 2500, '2011-01-01', '2011-12-31'),
(2, 3000, '2012-01-01', '2012-12-31'),
(2, 4000, '2013-01-01', '2013-12-31'),
(2, 5000, '2014-01-01', '2014-12-31'),
(3, 1000, '2010-01-01', '2010-12-31'), 
(3, 2500, '2011-01-01', '2011-12-31'),
(3, 3000, '2012-01-01', '2012-12-31');