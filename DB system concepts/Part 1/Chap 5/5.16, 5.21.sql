SET net_read_timeout=3600;
SET interactive_timeout=3600;
SET	global connect_timeout=3600;
SET SQL_SAFE_UPDATES=0;
delimiter $$
drop procedure if exists findAllPrereqs_withLevel $$
create procedure findAllPrereqs_withLevel() 
begin
	declare level_1 int default 0;
    declare	count int default 1;
    
    drop temporary table if exists prereq_with_level;
    create temporary table prereq_with_level(course_id varchar(8), prereq_id varchar(8), level_number int);
    
    select	count(distinct course_id) into @rowNumber
    from	prereq;
    
    while count <= @rowNumber do
		set level_1 = 0;
        drop temporary table if exists c_prereq;
		drop temporary table if exists new_c_prereq;
		drop temporary table if exists temp;
		create temporary table c_prereq(course_id varchar(8), level_number int);
		create temporary table new_c_prereq(course_id varchar(8));
		create temporary table temp(course_id varchar(8));
        
        select	course_id into @course_id_now
        from	(select distinct course_id, dense_rank() over(order by course_id) as 'row_number1' from prereq) as t1
        where	row_number1 = count;
		
		
		insert into new_c_prereq
			select	prereq_id
			from	prereq
			where	course_id = @course_id_now;
			
		repeat
			insert into c_prereq
				select	course_id, level_1
				from	new_c_prereq;
				
			set	level_1 = level_1 + 1;
				
			insert into temp
				select	prereq_id
				from	new_c_prereq join prereq using (course_id);
				
			delete from temp
				where	course_id in (select course_id from c_prereq);
				
			delete from new_c_prereq;
			insert into new_c_prereq
				select	*
				from	temp;
			delete from temp;
			
		until not exists(select * from new_c_prereq)
		end repeat;
        
        insert into prereq_with_level
        select	@course_id_now, course_id , level_number 	
        from	c_prereq;
        
        set count = count + 1;
        
    end while;
    
		select * from prereq_with_level;
        
end; $$

call findAllPrereqs_withLevel();
