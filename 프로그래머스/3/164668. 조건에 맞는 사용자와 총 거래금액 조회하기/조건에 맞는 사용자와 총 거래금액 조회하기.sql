-- 코드를 입력하세요
SELECT u.USER_ID, u.NICKNAME, sum(b.PRICE) as TOTAL_SALES
from USED_GOODS_USER u, USED_GOODS_BOARD b
where u.USER_ID = b.WRITER_ID
and b.STATUS = 'DONE'
group by u.USER_ID, u.NICKNAME
having sum(b.PRICE) >= 700000
order by TOTAL_SALES;