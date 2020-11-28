select	*
from	student left outer join takes using(ID)
where 	course_id is null;

select	*
from	student left outer join advisor on (student.ID = advisor.s_ID and i_id is not null)
where	i_ID is null;

select	*
from	employee left outer join manages on (employee.ID = manages.ID and manager_id is not null)
where	manages.ID is null;

select *
from	employee
where	ID not in (select distinct ID from manages where manager_id is not null);

create 	view tot_credits (year, num_credits) as 
	select	year, sum(credits)
    from	takes natural join course 
	group by year;


    