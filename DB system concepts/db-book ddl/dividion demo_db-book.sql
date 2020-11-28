
select	distinct T1.ID
from	takes as T1
where	not exists (
					select	course_id
					from	course as C
					where	dept_name = 'Biology'
							and C.course_id <> 'BIO-399'
							and C.course_id not in(
										select	T2.course_id
                                        from	takes as T2
                                        where	T1.ID = T2.ID
										));
                              


select	course_id
from	course
where	dept_name = 'Biology'and course_id <> 'BIO-399';

select	ID
from	takes
where	course_id = 'BIO-101'
		or course_id = 'BIO-301';
        
with	biology_course as 
		(
		select	distinct course_id
		from	course
		where	dept_name = 'Biology'and course_id <> 'BIO-399'
		)
select	takes.ID
from	takes , biology_course
where	takes.course_id in 
		(
		select	distinct course_id
		from	course
		where	dept_name = 'Biology' and course_id <> 'BIO-399'
		)
group by ID
having count( distinct takes.course_id) = (
											select	count(*)	
											from	biology_course
											);
                                            
select	distinct T1.ID
from	takes as T1 join course on T1.course_id = course.course_id
where	not exists
		(
		select	course_id
        from	course as C1
        where	C1.dept_name = 'Biology' and C1.course_id <> 'BIO-399'
				and	not exists
                (
                select	C2.course_id
                from	course as C2 join takes as T2 on T2.course_id = C2.course_id
                where	C2.course_id = C1.course_id
						and T2.ID = T1.ID
                )
		);

