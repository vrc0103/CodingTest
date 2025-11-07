SELECT a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(s.SALES * b.PRICE) as SALES
from BOOK b, AUTHOR a, BOOK_SALES s
where b.book_id = s.book_id
and b.author_id = a.author_id
and to_char(s.SALES_DATE, 'YYYY-MM') = '2022-01'
group by a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY
order by a.AUTHOR_ID asc, b.CATEGORY desc