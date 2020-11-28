set global log_bin_trust_function_creators = 1 ; 
delimiter $$
drop function if exists avg_salary ; 
create function avg_salary(c_name varchar(50)) 
returns double
begin
    
    select	avg(salary) into @avg_salary_num
    from	works
    where	company_name = c_name;
    
    return @avg_salary_num;
end; $$

delimiter ;

with	company_name_only as (select distinct company_name from works)
select	company_name
from	company_name_only 
where	avg_salary('First Bank Corporation') > avg_salary(company_name);

set global log_bin_trust_function_creators = 0 ;

