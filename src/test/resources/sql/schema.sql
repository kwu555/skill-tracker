drop table if exists member_skills;
drop table if exists skill;
drop table if exists member;


CREATE TABLE skill (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE member (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  forename VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL
);

CREATE TABLE member_skills (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  member_id int NOT NULL,
  skill_id int NOT NULL,
  skill_level int NOT NULL,

  FOREIGN KEY (member_id) REFERENCES member(id),
  FOREIGN KEY (skill_id) REFERENCES skill(id),
  UNIQUE(member_id,skill_id)
);

insert into skill (id,name) values (1, 'JAVA');
insert into skill (id,name) values (2, 'C');
insert into skill (id,name) values (3, 'C++');
insert into skill (id,name) values (4, 'PYTHON');
insert into skill (id,name) values (5, 'C#');

insert into member (id,forename, surname) values (1,'EDDARD','STARK');
insert into member (id,forename, surname) values (2,'SANSA','STARK');

insert into member_skills (id,member_id, skill_id, skill_level) values (1,1,1,1);