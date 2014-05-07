select t1.sub_name, t1.sem, t2.grade 

FROM (SELECT subjects.sub_id as ID, subject_name as sub_name, semester as sem FROM curriculum  
LEFT JOIN subjects ON curriculum.sub_id=subjects.sub_id  
WHERE curriculum.major_id=(SELECT major FROM students WHERE id=1)) AS t1  

LEFT JOIN 

(SELECT subjects.sub_id as ID, subject_name as sub_name, grade FROM grades  
LEFT JOIN subjects ON grades.subject_id=subjects.sub_id WHERE student_id=1) AS t2 

ON t1.ID=t2.ID where t1.sem=1;