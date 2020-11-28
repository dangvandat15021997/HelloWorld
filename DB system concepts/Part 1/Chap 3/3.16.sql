select	employee.ID, person_name
from	employee, works, company
where	employee.ID = works.ID
		and	works.company_name = company.company_name
        and	employee.city = company.city;
        
select	e1.ID, e1.person_name
from	employee as e1, employee as e2, manages
where	e1.ID = manages.ID
		and	e2.ID = manages.manager_id
        and	e1.city = e2.city
        and	e1.street = e2.street;
        
select	employee.ID, person_name
from	employee, works as w1, company
where	employee.ID = w1.ID
		and	w1.company_name = company.company_name
        and	w1.salary > (select avg(salary) from works as w2 where w2.company_name = w1.company_name);

select	company_name
from	worsks
where	salary = (select min(salary) from works);