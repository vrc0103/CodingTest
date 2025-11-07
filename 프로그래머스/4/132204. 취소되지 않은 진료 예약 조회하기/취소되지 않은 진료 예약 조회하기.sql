SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, a.APNT_YMD
from patient p, doctor d, appointment a
where a.pt_no = p.pt_no
and a.MDDR_ID = d.dr_id
and a.MCDP_CD = 'CS'
and trunc(a.APNT_YMD) = date '2022-04-13'
and a.APNT_CNCL_YN = 'N'
order by APNT_YMD asc