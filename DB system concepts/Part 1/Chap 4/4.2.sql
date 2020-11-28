--------------
select	ID, count(sec_id)
from	instructor left outer join teaches using (ID)
group by ID 
order by count(sec_id) desc;
--------------
(select	ID, count(sec_id) as countNumber
from	instructor  join teaches using (ID)
group by ID )
union
(select	ID, 0
 from	instructor
 where	ID not in (select distinct ID from teaches)
)
order by countNumber desc;
-------------
select	ID, 
		(select count(*) as NumberOfTeaches
         from teaches where teaches.ID = i.ID)
from	instructor as i;
-----------------
select	course_id, sec_id, ID, coalesce(name, '-') as name
from	section natural left outer join (teaches natural left outer join instructor)
where	(semester, year) = ('Spring', 2018);
------------------
select dept_name, count(ID)
from	department natural left outer join instructor
group by dept_name
