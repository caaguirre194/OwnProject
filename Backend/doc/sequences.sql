DROP SEQUENCE IF EXISTS
	sec_user;

DROP SEQUENCE IF EXISTS
    sec_person;

create sequence sec_user
  start with 1
  increment by 1
  cycle;

create sequence sec_person
  start with 1
  increment by 1
  cycle;