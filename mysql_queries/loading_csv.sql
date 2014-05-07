LOAD DATA LOCAL INFILE 'D:/BD/csv_files/faculties.csv'
INTO TABLE  faculties 
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(fac_name);


LOAD DATA LOCAL INFILE 'D:/BD/csv_files/majors.csv'
INTO TABLE  majors 
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(major_name, master_faculty);


LOAD DATA LOCAL INFILE 'D:/BD/csv_files/students.csv'
INTO TABLE  students 
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(first_name, middle_name, last_name, fnum, EGN, major, semester, uni_group, type);

LOAD DATA LOCAL INFILE 'D:/BD/csv_files/kst_subjects.csv'
INTO TABLE subjects
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(subject_name);

LOAD DATA LOCAL INFILE 'D:/BD/csv_files/kst_curriculum_csv.csv'
INTO TABLE curriculum
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(sub_id, semester, major_id);

LOAD DATA LOCAL INFILE 'D:/BD/csv_files/kst_grades_student_11.csv'
INTO TABLE grades
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(subject_id, student_id, grade);

LOAD DATA LOCAL INFILE 'D:/BD/csv_files/student_users.csv'
INTO TABLE users
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(stud_id, username, password);