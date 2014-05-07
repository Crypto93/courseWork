DROP DATABASE IF EXISTS kr17_test;

CREATE DATABASE kr17_test;

USE kr17_test;

CREATE TABLE students (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(35) NOT NULL,
last_name VARCHAR(35) NOT NULL,
fnum VARCHAR(20) NOT NULL,
EGN INT NOT NULL,
uni_group INT NOT NULL,
major INT NOT NULL,
semester TINYINT NOT NULL,
type TINYINT NOT NULL DEFAULT 0,
PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE users (
user_id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(35) NOT NULL,
password VARCHAR(35) NOT NULL,
type TINYINT NOT NULL,
stud_id INT NOT NULL,
PRIMARY KEY (user_id)
) ENGINE = InnoDB;

CREATE TABLE faculties (
fac_id INT NOT NULL AUTO_INCREMENT,
fac_name VARCHAR(255),
PRIMARY KEY (fac_id)
) ENGINE = InnoDB;

CREATE TABLE subjects (
sub_id INT NOT NULL AUTO_INCREMENT,
subject_name VARCHAR (255),
PRIMARY KEY (sub_id)
) ENGINE = InnoDB;

CREATE TABLE curriculum (
cur_id INT NOT NULL AUTO_INCREMENT,
major_id INT NOT NULL,
semester TINYINT NOT NULL,
sub_id INT NOT NULL,
PRIMARY KEY (cur_id)
) ENGINE = InnoDB;

CREATE TABLE majors (
major_id INT NOT NULL AUTO_INCREMENT,
major_name varchar(255) NOT NULL,
major_fac INT NOT NULL,
PRIMARY KEY (major_id)
) ENGINE = InnoDB;

CREATE TABLE grades (
grade_id INT NOT NULL AUTO_INCREMENT,
student_id INT NOT NULL,
subject_id INT NOT NULL,
grade FLOAT NOT NULL DEFAULT 2,
PRIMARY KEY (grade_id)
) ENGINE = InnoDB;

ALTER TABLE students MODIFY COLUMN EGN BIGINT NOT NULL;
ALTER TABLE students ADD COLUMN middle_name VARCHAR(35) DEFAULT NULL;
ALTER TABLE majors CHANGE COLUMN major_fac master_faculty INT NOT NULL; 

ALTER TABLE students ADD CONSTRAINT FOREIGN KEY(major) REFERENCES majors(major_id);
ALTER TABLE users ADD CONSTRAINT FOREIGN KEY(stud_id) REFERENCES students(id);
ALTER TABLE curriculum ADD CONSTRAINT FOREIGN KEY(major_id) REFERENCES majors(major_id);
ALTER TABLE curriculum ADD CONSTRAINT FOREIGN KEY(sub_id) REFERENCES subjects(sub_id);
ALTER TABLE majors ADD CONSTRAINT FOREIGN KEY(master_faculty) REFERENCES faculties(fac_id);
ALTER TABLE grades ADD CONSTRAINT FOREIGN KEY(student_id) REFERENCES students(id);
ALTER TABLE grades ADD CONSTRAINT FOREIGN KEY(subject_id) REFERENCES subjects(sub_id);

DESC students;
DESC users;
DESC faculties;
DESC subjects;
DESC curriculum;
DESC majors;
DESC grades;

DROP USER student@localhost;
CREATE USER student@localhost IDENTIFIED BY 'student123';
GRANT SELECT ON kr17_test.* TO student@localhost;
GRANT UPDATE ON kr17_test.users TO student@localhost;


 