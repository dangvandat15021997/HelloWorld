update	works
set	salary = salary * 1.1
where	company_name = 'First Bank Corporation';

update	works
set	salary = (
				select	salary * 1.1
                from	works, manages
                where	works.ID = manages.manager_id
				);
                
delete from	works
where	conpany_name = 'Small Bank Corporation';

