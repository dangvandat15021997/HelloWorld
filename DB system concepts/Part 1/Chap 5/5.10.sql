----------------- 5.10
delimiter $$
drop procedure if exists display_data $$
create procedure display_data(in method varchar(20), out rc int)
begin 
	if(upper(method) = 'YEAR') then
		drop temporary table if exists t1;
        create  temporary table t1
		select	distinct year(date) as 'year'
			, sum(ifnull(dollar_volume, 0)) as 'number of shares traded'
            , count(*) as 'number of trades'
			, sum(ifnull(dollar_volume, 0)) as 'total dollar volume' 
		from	nyse 
		group by year(date);
		
		set rc = 0;
    
    elseif(upper(method) = 'MONTH') then 
		drop temporary table if exists t1;
        create  temporary table t1
		select	distinct month(date) as 'month'
			, sum(ifnull(dollar_volume, 0)) as 'number of shares traded'
            , count(*) as 'number of trades'
			, sum(ifnull(dollar_volume, 0)) as 'total dollar volume' 
		from	nyse 
		group by month(date);
    
		set rc = 0;
        
	 elseif(upper(method) = 'DAY') then 
		drop temporary table if exists t1;
        create  temporary table t1
		select	distinct day(date) as 'day'
			, sum(ifnull(dollar_volume, 0)) as 'number of shares traded'
            , count(*) as 'number of trades'
			, sum(ifnull(dollar_volume, 0)) as 'total dollar volume' 
		from	nyse 
		group by day(date);
    
		set rc = 0;
	
    else 
		set rc = 1;
		select 'Don t have this option' as 'Alert';
	end if;
end $$
delimiter ;
call display_data('day', @rc);
-------------------------- rollup
select	if(grouping(year(date)), 'all years', year(date)) as 'year'
		, if(grouping(month(date)), 'all month', month(date)) as 'month'
        , if(grouping(day(date)), 'all month', day(date)) as 'day'
        , sum(ifnull(dollar_volume, 0)) as 'number of shares traded'
		, count(*) as 'number of trades'
		, sum(ifnull(dollar_volume, 0)) as 'total dollar volume' 
from	nyse
group by year(date), month(date), day(date) with rollup;