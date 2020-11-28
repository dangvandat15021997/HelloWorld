delimiter $$
drop procedure if exists print_Instructor_by_substring;
create procedure print_Instructor_by_substring(in subtring varchar(50)) 
begin
	select	*
    from	instructor
    where	instructor.name like '%subtring%';
end;  $$
delimiter ;
--------------------------- 
delimiter $$
drop procedure if exists print_Instructor_ID;
create	procedure print_Instructor_ID(in p_ID varchar(10))
begin
	if(cast(p_ID as unsigned) < 0 or cast(p_ID as unsigned) > 99999) then
		signal sqlstate  '45000' set message_text = 'Id num ber must be in range of 0-9999';
    else if (not exists(select course_id from teaches where	ID = p_id )) then
		signal sqlstate '45000' set message_text = 'Instructor with id is not exists'; 
	end if;	
		select	instructor.dept_name, course_id, title, sec_id, semester, year, count(*) as 'total enrollment'
        from	instructor natural join teaches
				natural join section
                inner join takes using (course_id, sec_id, semester, year)
                join course using(course_id)
		where	instructor.ID = p_id;
	end if;
end; $$
delimiter ;
call print_Instructor_ID('43779');