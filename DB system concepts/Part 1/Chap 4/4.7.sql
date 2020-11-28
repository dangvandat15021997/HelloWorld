create database Employee;
use Employee;

create table employee 
	(
    ID				varchar(5),
    person_name		nvarchar(20),
	street			nvarchar(20),
    city			nvarchar(20),
    constraint	employee_pk primary key (ID)
    );
    
create table works
	(
    ID				varchar(5),
    company_name	nvarchar(20),
    salary			numeric(10),
    constraint	works_pk primary key (ID),
    constraint	works_employee_fk foreign key (ID) references employee(ID),
    constraint	works_company_fk foreign key (company_name) references company(company_name)
    );
    
create table company 
	(
    company_name	nvarchar(20),
    city			nvarchar(20),
    constraint	companny_name_pk primary key (company_name)
    );

create table manages
	(
    ID				varchar(5),
    manager_id		varchar(5),
    constraint	manages_pl primary key (ID),
    constraint	manages_employee_id_fk foreign key (ID) references employee(ID),
    constraint	manages_manager_id foreign key (manager_id) references employee(ID)
    );
