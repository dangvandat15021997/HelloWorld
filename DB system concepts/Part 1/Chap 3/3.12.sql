insert into section(course_id, sec_id, semester, year, building, room_number, time_slot_id)
values ('CS-001', 1, 'Fall', 2017, null, null, null);

insert into	takes(ID, course_id, sec_id, semester, year, grade)
	select	student.ID, section.course_id, sec_id, semester, year, null 
    from	student, section
    where	student.dept_name = 'Comp. Sci.'
			and	(section.course_id, sec_id, semester, year) = ('CS-001', 1, 'Fall', 2017);
            
delete from takes
	where	ID = '12345'
			and (course_id, sec_id, semester, year) = ('CS-001', 1, 'Fall', 2017);
            
delete from course
	where	course_id = 'CS-001';

delete from takes as T
	where	T.course_id in 
								(
								select	course.course_id
                                from	course, section as S
                                where	(T.course_id, T.sec_id, T.semester, T.year) = ((S.course_id, S.sec_id, S.semester, S.year))
										and S.course_id = course.course_id
                                        and lower(course.title) like '%advanced%'
								);