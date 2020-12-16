select 
cName as '学科',
count(*) as '总数',
sum(case when weekDay = 0  then 1 else 0 end) as '周日',
sum(case when weekDay = 1  then 1 else 0 end) as '周一',
sum(case when weekDay = 2  then 1 else 0 end) as '周二',
sum(case when weekDay = 3  then 1 else 0 end) as '周三',
sum(case when weekDay = 4  then 1 else 0 end) as '周四',
sum(case when weekDay = 5  then 1 else 0 end) as '周五',
sum(case when weekDay = 6  then 1 else 0 end) as '周六',
sum(case when h>=6 and h<9 then 1 else 0 end) as '6-9点',
sum(case when h>=9 and h<12 then 1 else 0 end) as '9-12点',
sum(case when h>=12 and h<15 then 1 else 0 end) as '12-15点',
sum(case when h>=15 and h<16 then 1 else 0 end) as '15-16点',
sum(case when h>=16 and h<17 then 1 else 0 end) as '16-17点',
sum(case when h>=17 and h<18 then 1 else 0 end) as '17-18点',
sum(case when h>=18 and h<19 then 1 else 0 end) as '18-19点',
sum(case when h>=19 and h<20 then 1 else 0 end) as '19-20点',
sum(case when h>=20 and h<23 then 1 else 0 end) as '20-23点'

from 
(
select 
topicDiscussionId,
(TIMESTAMPDIFF(DAY,'2017-01-01',createTime))%7 as weekDay,
createTime,
hour(createTime) as h,
case when curriculumId in (1,2,3) then cname else '其他' end as cName
from 
(
select 
t.topicDiscussionId,
t.createTime,
t.curriculumId,
bc.cname

from yxxs_topicdiscussion as t 
left join yxxs_basecurriculum as bc on t.curriculumId = bc.cid
where t.createTime > '2017-01-01'
) as tt
) as ttt
group by cName