select 
name as '学校名称',
count(distinct topicDiscussionId) as '总数',
d as '日期'
from 
(
select 
t.topicDiscussionId,
t.createTime,
DATE_FORMAT(t.createTime,'%Y-%m-%d') as d,
po.organizationId,
po.name
from yxxs_topicdiscussion as t left join 
(
select topicDiscussionId,max(deliveryToGroupId) as deliveryToGroupId from
yxxs_topicdiscussiondelivery where deliveryToGroupId!=0 group by topicDiscussionId
)as td on t.topicDiscussionId=td.topicDiscussionId
left join group_ as g on td.deliveryToGroupId=g.groupId
left join organization_ as o on g.classPK=o.organizationId
left join organization_ as po on o.parentOrganizationId=po.organizationId

where t.createTime > '2017-01-01' and t.type_ = 0 and po.organizationId is not null
) as tt
group by organizationId,d
order by organizationId desc, d desc