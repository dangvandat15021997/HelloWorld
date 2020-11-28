select	* from student natural join takes
union
select s1.*, null, null, null, null, null
from student as s1 where not exists(select  ID from takes where takes.ID = s1.ID);
-----
select	* from student natural join takes
union
select s1.*, null, null, null, null, null
from student as s1 where not exists(select  ID from takes where takes.ID = s1.ID)
union
select ID, null, null, null, course_id, sec_id, semester, year, grade 
from takes as t1 where not exists(select  ID from student where t1.ID = student.ID);
