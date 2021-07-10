drop table if exists skill;
drop table if exists member;
drop table if exists member_skills;

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