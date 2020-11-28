select	course_id
from	course
group by course_id
having	count(distinct title) = 1;

select	dept_name
from	instructor 
group by dept_name
having	sum(salary) > (
						select	sum(salary) / (count(distinct dept_name))
						from	instructor
						);

select	student.ID, student.name
from	student, advisor, instructor
where	advisor.s_ID = student.ID
		and	advisor.i_ID = instructor.ID
        and	student.dept_name = 'Accounting'
        and	instructor.dept_name = 'Physics';
        
select	dept_name
from	department
where	budget > (select budget from department where dept_name = 'Philosophy');

select	distinct ID,  course_id
from	takes
group by ID, course_id
having count(course_id) >= 2;

with	list_2times_takes as (
								select	distinct ID,  course_id
								from	takes
								group by ID, course_id
								having count(course_id) >= 2
								)
select	ID
from	list_2times_takes
group by ID
having	count(course_id) >= 3;

select	distinct i1.ID
from	instructor as i1
where	not exists (
					select	course_id
					from	course 
					where	i1.dept_name = course.dept_name
							and	course.course_id not in (
														select	distinct course_id
														from	teaches as t1
														where	i1.ID = t1.ID
														)
					)
order by i1.name;


select	student.ID
from	student inner join takes on student.ID = takes.ID and student.name like 'D%'
		and student.dept_name = 'History'
		inner join course on takes.course_id = course.course_id  and course.dept_name = 'Music'
group by student.ID
having	count(takes.course_id) < 5;

(select	distinct i1.ID, i1.name
from	instructor as i1 inner join teaches as t1 on i1.ID = t1.ID
		inner join takes as t2 on (t1.course_id, t1.sec_id, t1.semester, t1.year) = (t2.course_id, t2.sec_id, t2.semester, t2.year)
where	t2.grade <> 'A')
union
(
select	ID, name
from	instructor
where	ID not in (select distinct ID from teaches)
);

select	i1.ID, i1.name
from	instructor as i1 inner join teaches as t1 on i1.ID = t1.ID
		inner join takes as t2 on (t1.course_id, t1.sec_id, t1.semester, t1.year) = (t2.course_id, t2.sec_id, t2.semester, t2.year)
where	t2.grade <> 'A'
group by i1.ID, i1.name
having	sum(case when t2.grade is  not null then 1 else 0 end) >= 1;

select	c1.course_id, c1.title
from	course as c1 inner join section as s1 on c1.course_id = s1.course_id
		inner join time_slot as ts1 on s1.time_slot_id = ts1.time_slot_id
where	c1.dept_name = 'Comp. Sci.'
		and (case
			 when (ts1.end_hr = 12 and ts1.end_min > 0) then true
			 when ts1.end_hr > 12 then true
			 else false
			 end
			 )
group by c1.course_id, c1.title
having count(*) >= 1;

select	t1.course_id, t1.sec_id, t1.year, t1.semester, count(distinct ID) as num
from	section as s1 inner join takes as t1 on 
		(s1.course_id, s1.sec_id, s1.year, s1.semester) = (t1.course_id, t1.sec_id, t1.year, t1.semester)
group by	t1.course_id, t1.sec_id, t1.year, t1.semester
having	count(distinct ID) > 0;

with	sections_num as (
						select	t1.course_id, t1.sec_id, t1.year, t1.semester, count(distinct ID) as num
						from	section as s1 inner join takes as t1 on 
						(s1.course_id, s1.sec_id, s1.year, s1.semester) = (t1.course_id, t1.sec_id, t1.year, t1.semester)
						group by	t1.course_id, t1.sec_id, t1.year, t1.semester
						having	count(distinct ID) > 0
						)
select	course_id, sec_id, year, semester, num
from	sections_num
where	num = (select max(num) from sections_num);


        
					
							





