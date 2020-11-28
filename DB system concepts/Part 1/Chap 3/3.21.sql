select 	member.memb_no, member.name
from	member, book, borrowed
where	member.memb_no = borrowed.memb_no
		and book.isbn = borrowed.isbn
        and	book.publisher = 'McGraw-Hill';
        
select 	member.memb_no, member.name
from	member, book, borrowed
where	member.memb_no = borrowed.memb_no
		and book.isbn in (
							select	distinct isbn
                            from	book
                            where	publisher = 'McGraw-Hill'
						)
group by member.memb_no, member.name
having	count(distinct isbn)  = (
								select	count(distinct isbn)
								from	book
								where	publisher = 'McGraw-Hill'
								);
   
select 	book.publisher, member.memb_no, member.name
from	member, book, borrowed
where	member.memb_no = borrowed.memb_no
		and book.isbn = borrowed.isbn
group by book.publisher, member.memb_no, member.name
having	count(distinct book.isbn) > 5;

select	sum(isbn)/count(member.memb_no)
from	borrowed, member;




		
                                
