drop table AppUser IF exists;

create table AppUser (
  id bigint generated by Default as identity (start with 1, increment by 1) not NULL,
  username varchar(100) not NULL,
  password varchar(200) not NULL,
  primary key (id),
  CONSTRAINT AppUser_Name_Unique UNIQUE (username)
);

drop table LecturePrep IF exists;

create table LecturePrep (
  id int generated by Default as identity (start with 1, increment by 1) not NULL,
  title varchar(100) not NULL,
  content varchar(300) not NULL,
  primary key (id),
  CONSTRAINT LecturePrep_Title_Unique UNIQUE (title)
);