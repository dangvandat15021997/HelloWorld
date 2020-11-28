create view students_grades as
select	ID, sum_points/(decode(sum_credits, 0, null, sum_credits)) 
from	(
		select	ID, sum(decode(credits, null, 0, credits)) as sum_credits,
				sum(decode(grade, null, 0, credits*points)) as sum_points
		from	takes natural join course natural left outer join grade_points
        group by ID			
		)
union 
select ID, null
from	student
where ID not in (select distinct ID from takes);
-----
