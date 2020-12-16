SELECT
	o.NAME AS 学校,
	CONCAT(ap.pName,'-',ac.cName,'-',ad.dName) AS 地区,
	ocm_s.tnum AS 现有教师总数,
	IF (isnull(a_taskTNum),0,a_taskTNum) AS 发布任务教师数_16_01_01起,
	IF (isnull(taskTNum),0,taskTNum) AS 阶段发任务教师数,
	IF (isnull(all_task_cnt),0,all_task_cnt) AS 任务数_16_01_01起,
	IF (isnull(task_cnt),0,task_cnt) AS 阶段任务数,
	IF (isnull(ag_task_cnt),0,ag_task_cnt) AS 高互动率任务数_16_01_01起,
	IF (isnull(g_task_cnt),0,g_task_cnt) AS 阶段高互动率任务数,
	tttt.classCnt AS 班级总数,
	tttt.a_taskClassCnt AS 发任务班级数_16_01_01起,
	tttt.taskClassCnt AS 阶段发任务班级数,
	IF (isnull(ocm_s.a_stu_cnt),0,ocm_s.a_stu_cnt) AS 学生总数,
	IF (isnull(loginStuCnt),0,loginStuCnt) AS 阶段登录学生数,
	IF (isnull(ahfcount),0,ahfcount) AS 上传作业学生数_16_01_01起,
	IF (isnull(hfcount), 0, hfcount) AS 阶段上传作业学生数,
	a_rank AS 任务平均互动率_16_01_01起,
	rank AS 阶段任务平均互动率,
	IF (isnull(am_task_cnt),0,am_task_cnt) AS 中互动率任务数_16_01_01起,
	IF (isnull(m_task_cnt),0,m_task_cnt) AS 阶段中互动率任务数,
	IF (isnull(al_task_cnt),0,al_task_cnt) AS 低互动率任务数_16_01_01起,
	IF (isnull(l_task_cnt),0,l_task_cnt) AS 阶段低互动率任务数,
	cDate AS 最后建班级时间,
	u.emailAddress AS 运营账号,
	ocm_s.tnum_wxb AS 现有教师绑定总数,
	IF (isnull(ocm_s.a_stu_wxb_cnt),0,ocm_s.a_stu_wxb_cnt) AS 学生绑定总数
FROM
(
	SELECT
		count(classId) AS classCnt,
		sum(IF(taskCnt > 0, 1, 0)) AS taskClassCnt,
		sum(IF(a_taskCnt > 0, 1, 0)) AS a_taskClassCnt,
		max(cDate) as cDate,
		schoolId
	FROM
		(
			SELECT
				classId,
				schoolId,
				sum(IF (td.createTime is null or td.createTime <=@var_taskDay,0,1)) AS taskCnt,
				sum(IF (td.createTime is null or td.createTime <=@init_startTime,0,1)) AS a_taskCnt,
				max(createDate) as cDate
			FROM
			view_yxxs_classinfo as v_ci
			left join yxxs_topicdiscussiondelivery td ON v_ci.groupId = td.deliveryToGroupId
			left join yxxs_organization_ext as oe on v_ci.classId = oe.organizationId
			where oe.orgType in(1025 ,1027)
			GROUP BY
				classId,schoolId
		) AS t
	GROUP BY
		schoolId
) AS tttt
LEFT JOIN organization_ AS o ON tttt.schoolId = o.organizationId
LEFT JOIN yxxs_organization_ext AS oe ON o.organizationId = oe.organizationId
LEFT JOIN user_ AS u ON oe.defaultManagerId = u.userId
LEFT JOIN yxxs_address AS a ON o.organizationId = a.classPK AND classNameId = 56702
LEFT JOIN yxxs_province AS ap ON a.province = ap.pid
LEFT JOIN yxxs_city AS ac ON a.city = ac.cid
LEFT JOIN yxxs_district AS ad ON a.district = ad.did
left join (
	select 
	schoolId,
	sum(case when tp.createTime > @var_taskDay then 1 else 0 end) as hfcount,
	sum(case when tp.createTime > @init_startTime then 1 else 0 end) as ahfcount
	from 
	(select topicDiscussionId,type from view_yxxs_topic_user_action where topicDiscussionId >0 and type = 'f') as v_tua 
	left join view_yxxs_topic_info as v_ti on v_tua.topicDiscussionId = v_ti.topicDiscussionId
	left join yxxs_topicdiscussion as tp on v_tua.topicDiscussionId = tp.topicDiscussionId
	group by schoolId
) as hc on hc.schoolId = tttt.schoolId
left join (
	SELECT
	schoolid,
	avg(case when td.createTime > @var_taskDay then rank else null end) as rank,
	sum(case when td.createTime > @var_taskDay then 1 else 0 end) as task_cnt,
	count(distinct(case when td.createTime > @var_taskDay then td.userId else null end)) as taskTNum,
	avg(case when td.createTime > @init_startTime then rank else null end) as a_rank,
	count(distinct(case when td.createTime > @init_startTime then td.userId else null end)) as a_taskTNum,
	sum(case when td.createTime > @init_startTime then 1 else 0 end) as all_task_cnt,
	
	sum(IF (rank > 0.3 AND rank <= 0.5 AND createTime >@var_taskDay, 1, 0)) m_task_cnt,
	sum(IF (rank <= 0.3 AND createTime >@var_taskDay, 1, 0)) AS l_task_cnt,
	sum(IF (rank > 0.5 AND createTime >@var_taskDay, 1, 0)) AS g_task_cnt,
	sum(IF (rank > 0.3 AND rank <= 0.5 AND createTime >@init_startTime, 1, 0)) AS am_task_cnt,
	sum(IF (rank <= 0.3 AND createTime >@init_startTime, 1, 0)) AS al_task_cnt,
	sum(IF (rank > 0.5 AND createTime >@init_startTime, 1, 0)) AS ag_task_cnt
	FROM
	(select topicDiscussionId,schoolId 
	   from view_yxxs_topic_info force index(index_view_yxxs_topic_info) 
	  WHERE topicDiscussionId > 0 and schoolId > 0
	) AS v_ti
	left join yxxs_topicdiscussion as td on v_ti.topicDiscussionId = td.topicDiscussionId
	left join yxxs_topicdiscussionrank_ext as te on v_ti.topicDiscussionId = te.topicDiscussionId
	where schoolId>0
	group by schoolId
) as trank ON tttt.schoolId = trank.schoolid
left join (
	select 
	count(distinct case when FIND_IN_SET(14214,roles) then u.userId else null end) as a_stu_cnt,
	count(distinct case when FIND_IN_SET(14214,roles) and u.lastLoginDate >@var_taskDay then u.userId else null end) as loginStuCnt,
	count(distinct case when FIND_IN_SET(14216,roles) then u.userId else null end) as tnum,
	schoolId 
	from (select userId,schoolId,roles 
	        from view_yxxs_user_class_school FORCE INDEX(pk_view_yxxs_user_class_school_userid) 
	        left join yxxs_organization_ext on classId=organizationId
	       WHERE userId > 0 and schoolId > 0 and orgType in (1025,1027)
	     )AS v_ucs
	left join user_ as u on v_ucs.userid = u.userId
	group by schoolId
) AS ocm ON tttt.schoolId = ocm.schoolId
left join (
	select 
	COUNT(DISTINCT CASE WHEN roleId=14214 THEN ugr.userId ELSE NULL END) AS a_stu_cnt,
	COUNT(DISTINCT CASE WHEN roleId=14214 AND wxb.openId IS NOT NULL THEN ugr.userId ELSE NULL END) AS a_stu_wxb_cnt,
	COUNT(DISTINCT CASE WHEN roleId=14216 THEN ugr.userId ELSE NULL END) AS tnum,
	COUNT(DISTINCT CASE WHEN roleId=14216 AND wxb.openId IS NOT NULL THEN ugr.userId ELSE NULL END) AS tnum_wxb,
	o.parentorganizationid as schoolId 
	from (SELECT b.*
		    FROM yxxs_class_members a,yxxs_class_stage c,usergrouprole b
		   WHERE c.active_ = 1023
		     AND a.classid = c.classid
		     AND a.userid = b.userId
		     AND a.roleId = b.roleId
		     AND c.groupId = b.groupId
		 ) ugr
	left join group_ as g on ugr.groupId = g.groupId
	left join organization_ as o on o.organizationId=g.classPK 
	left join yxxs_organization_ext as oe on o.organizationId=oe.organizationId
	LEFT JOIN yxxsweixin_bindweixin wxb ON ugr.userid = wxb.userId
	where o.organizationId is not null and oe.orgType in (1025) and g.companyId=10154 and classNameId=10003
	group by schoolId
) AS ocm_s ON tttt.schoolId = ocm_s.schoolId