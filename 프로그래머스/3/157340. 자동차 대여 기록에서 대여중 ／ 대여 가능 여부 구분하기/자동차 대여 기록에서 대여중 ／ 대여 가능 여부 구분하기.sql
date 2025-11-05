-- 코드를 입력하세요
SELECT distinct
    CAR_ID, 
    case
        when sum(
            case 
                when START_DATE <= date '2022-10-16' and END_DATE >= date '2022-10-16'
                    then 1
                else 0
            end
        ) > 0
            then '대여중'
        else '대여 가능'
    end as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
order by CAR_ID desc;