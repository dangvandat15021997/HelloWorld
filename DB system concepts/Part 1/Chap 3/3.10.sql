A.
update	employee
set	city = 'Newtown'
where	ID = '12345';

B.
update	works as w1
set	salary = salary*
	(case
		when salary <= 100000 then 1.1
        else 1.03
	end)
where	w1.ID in (select distinct manager_id from manages)
		and w1.company_name = 'First Bank Corportion';
        

	