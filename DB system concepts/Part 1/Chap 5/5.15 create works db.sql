create database works;

use works;

create table employee(
	employee_name	varchar(50),
    street			varchar(50),
    city			varchar(50),
    constraint employee_pk primary key (employee_name)
);

create table works (
	employee_name	varchar(50),
    company_name	varchar(50),
    salary			numeric(10),
    constraint works primary key (employee_name)
);

INSERT INTO 
    employee 
        (`employee_name`, `street`, `city`)
    VALUES
        ('Employee 01', '1234 State ST.', 'Your City'),
        ('Employee 02', '1234 State ST.', 'Your City'),
        ('Employee 03', '1234 State ST.', 'Your City'),
        ('Employee 04', '1234 State ST.', 'Your City'),
        ('Employee 05', '1234 State ST.', 'Your City'),
        ('Employee 06', '1234 State ST.', 'Your City'),
        ('Employee 07', '1234 State ST.', 'Your City'),
        ('Employee 08', '1234 State ST.', 'Your City'),
        ('Employee 09', '1234 State ST.', 'Your City'),
        ('Employee 10', '1234 State ST.', 'Your City'),
        ('Employee 11', '1234 State ST.', 'Your City'),
        ('Employee 12', '1234 State ST.', 'Your City'),
        ('Employee 13', '1234 State ST.', 'Your City'),
        ('Employee 14', '1234 State ST.', 'Your City'),
        ('Employee 15', '1234 State ST.', 'Your City'),
        ('Employee 16', '1234 State ST.', 'Your City'),
        ('Employee 17', '1234 State ST.', 'Your City'),
        ('Employee 18', '1234 State ST.', 'Your City'),
        ('Employee 19', '1234 State ST.', 'Your City'),
        ('Employee 20', '1234 State ST.', 'Your City'),
        ('Employee 21', '1234 State ST.', 'Your City'),
        ('Employee 22', '1234 State ST.', 'Your City');


INSERT INTO 
    works 
        (`employee_name`, `company_name`, `salary`)
    VALUES
        ('Employee 01', 'Company 01', 10000.00),
        ('Employee 02', 'Company 01', 10000.00),
        ('Employee 03', 'Company 01', 10000.00),
        ('Employee 04', 'Company 01', 10000.00),
        ('Employee 05', 'Company 01', 10000.00),
        ('Employee 06', 'Company 02', 15000.00),
        ('Employee 07', 'Company 02', 15000.00),
        ('Employee 08', 'Company 02', 15000.00),
        ('Employee 09', 'Company 03', 20000.00),
        ('Employee 10', 'Company 03', 20000.00),
        ('Employee 11', 'Company 03', 20000.00),
        ('Employee 12', 'Company 03', 20000.00),
        ('Employee 13', 'Company 03', 20000.00),
        ('Employee 14', 'Company 03', 20000.00),
        ('Employee 15', 'Company 03', 20000.00),
        ('Employee 16', 'Company 04', 60000.00),
        ('Employee 17', 'Company 04', 60000.00),
        ('Employee 18', 'Company 04', 60000.00),
        ('Employee 19', 'First Bank Corporation', 17000.00),
        ('Employee 20', 'First Bank Corporation', 17000.00),
        ('Employee 21', 'First Bank Corporation', 17000.00),
        ('Employee 22', 'First Bank Corporation', 17000.00);

