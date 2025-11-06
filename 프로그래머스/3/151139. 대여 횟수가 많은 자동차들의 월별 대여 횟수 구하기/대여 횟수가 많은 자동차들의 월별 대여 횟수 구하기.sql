-- 코드를 입력하세요
select
    EXTRACT(MONTH FROM start_date) as MONTH,
    CAR_ID,
    count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
where start_date between date '2022-08-01' and date '2022-10-31'
and car_id in (
        select car_id
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where start_date between date '2022-08-01' and date '2022-10-31'
        group by car_id
        having count(*) >= 5
    )
group by EXTRACT(MONTH FROM start_date), CAR_ID
order by MONTH, CAR_ID desc;