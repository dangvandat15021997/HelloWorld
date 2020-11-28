select count(participated.report_number)
from	person, participated, owns 
where	person.name = 'John Smith'
		and person.driven_id = participated.driven_id
        and person.driven_id = owns.driven_id
        and	owns.license_plate = participated.license_plate
group by participated.driven_id;

update	participated
set	participated.damage_amount = 3000
where	license_plate = 'AABB2000'
		and report_number = 'AR2197';