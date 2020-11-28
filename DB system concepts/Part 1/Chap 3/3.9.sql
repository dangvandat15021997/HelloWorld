A.
select	ID, name, city
from	employee inner works on employee.ID = works.ID
where	works.name = 'First Bank Corporation';
B.
select	ID, name, city
from	employee inner works on employee.ID = works.ID
where	works.company_name = 'First Bank Corporation' and works.salary > 10000;
C.
select	ID
from	works
where	ID not in 
				(
					select	ID
					from	works
					where	works.company_name = 'First Bank Corporation'
				);
D.
select	ID
from	works
where	works.salary > all 
				(
					select	salary
					from	works
					where	works.company_name = 'Small Bank Corporation'
				);
 E.               
with	SmallBank_city as
						(
                        select	city
                        from	company
                        where	company_name = 'Small Bank Corporation'
                        )
select	company_name
from	company, SmallBank_city
where	not exists ((SmallBank_city)
					except
                    (
                    select	city
                    from	company
                    ));
F.
with	companyName_countEm as 
							(
                            select	distinct company_name, count(distinct ID) as numbersEm
                            from	works
                            group by company_name
                            )
select	company_name
from	companyName_countEm
where	numbersEm = (select max(numbersEm) from companyName_countEm);
G.
select	company_name
from	works 
group by company_name
having	avg(salary) >= all
						(
                        select	avg(salary)
                        where	company_name = 'First Bank Corporation'
                        group by company_name
                        );
                        
                        
        

