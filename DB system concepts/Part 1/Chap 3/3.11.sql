A.
select	ID, name
from	student
where	ID in (
				select	distinct takes.ID
                from	takes, course
                where	takes.course_id = course.course_id
						and course.dept_name = 'Comp. Sci.'
				);
B.                
select	ID, name
from	student
where	ID not in (
				select	distinct ID
                from	takes
                where	year < 2017
				);
C.
select	dept_name, max(salary) as salary_max
from	instructor
group by dept_name;
D.
with salary_max as (
					select	dept_name, max(salary) as salary_max
					from	instructor
					group by dept_name
					)
select	min(salary_max)
from	salary_max;

