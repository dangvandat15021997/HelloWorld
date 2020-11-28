create database car;
use car;

create table person(
	driven_id	varchar(10),
    name		nvarchar(20),
    address		nvarchar(30),
    constraint person_pk primary key (driven_id)
);
    
create table car(
	license_plate	varchar(10),
    model			nvarchar(20),
    year 			int check (year > 0),
    constraint car_pk primary key (license_plate)
);

create table accident(
	report_number	varchar(10),
    year 			int check (year > 0),
    location		nvarchar(30),
    constraint accident_pk primary key (report_number)
);

create table participated(
	report_number	varchar(10),
    license_plate	varchar(10),
    driven_id		varchar(10),
    damage_amount	numeric(10,0) check (damage_amount > 0),
    constraint participated_pk primary key (report_number, license_plate),
    constraint participated_accident_fk foreign key (report_number) references accident(report_number),
    constraint participated_car_fk foreign key (license_plate) references car(license_plate),
    constraint participated_person_fk foreign key (driven_id) references person(driven_id)
);

create table owns(
	driven_id		varchar(10),
    license_plate	varchar(10),
	constraint owns_pk primary key (driven_id, license_plate),
    constraint owns_person_fk foreign key (driven_id) references person(driven_id), 
    constraint owns_car_fk foreign key (license_plate) references car(license_plate)
);	