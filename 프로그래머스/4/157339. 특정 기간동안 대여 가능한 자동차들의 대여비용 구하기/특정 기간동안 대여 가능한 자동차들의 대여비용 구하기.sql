SELECT c.CAR_ID, c.CAR_TYPE, c.daily_fee * 30 * (100 - p.DISCOUNT_RATE) / 100 as FEE
from
    CAR_RENTAL_COMPANY_CAR c,
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
where c.car_type = p.car_type
and c.car_type in ('세단', 'SUV')
and c.car_id not in (
    select distinct car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date <= date '2022-11-30'
        and end_date >= date '2022-11-01'
)
and p.DURATION_TYPE = '30일 이상'
and c.daily_fee * 30 * (100 - p.DISCOUNT_RATE) / 100 >= 500000
and c.daily_fee * 30 * (100 - p.DISCOUNT_RATE) / 100 < 2000000
order by fee desc, car_type asc, car_id desc;