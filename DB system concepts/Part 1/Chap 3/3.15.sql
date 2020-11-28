select	C1.*
from	cusomter as C1 
where	not exists (
					select	distin branch_name
                    from	branch as C1
                    where	C1.branch_city = 'Brooklyn'
							and C1.branch_name not in (
													select	distinct C2.branch_name
                                                    from	depositor, account, branch as C2
                                                    where	depositor.ID = C1.ID
															and	depositor.account_number = account.account_number
                                                            and	account.branch_name = C2.branch_name
													)
					);
                    
select	sum(amount)
from	loan;

select	branch_name
from	branch
where	assets > some (
						select	assests
                        from	branch
                        where	branch_city = 'Brooklyn'
						);


                            
                            
                            
        
		