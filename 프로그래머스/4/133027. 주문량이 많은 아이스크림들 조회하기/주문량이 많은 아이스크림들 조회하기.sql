SELECT FLAVOR
from 
(
    select FLAVOR, sum(TOTAL_ORDER) as SUM
    from 
    (
        select * from FIRST_HALF
        union 
        select * from JULY j
    )
    group by flavor
    order by SUM desc
) t
where ROWNUM <= 3