SELECT
	CONCAT(u.lastName, u.firstName) AS 教师姓名,
	u.emailAddress AS 账号,
	openId AS 微信,
	(SELECT number_ FROM phone WHERE userId = u.userId LIMIT 1) AS 电话,
	schoolName AS 学校,
	u.createDate AS 注册日期,
	lastTime AS 最后发任务时间,
	firstTime As 首发任务时间,
	u.loginDate AS 最近登陆时间,
	classCnt AS 班级数,
	classNames AS 所在班级,
	cnt_stu AS 学生总数,
	IF (isnull(taskCnt), 0, taskCnt) AS 阶段任务数量,
	u.all_taskCnt AS 任务总数,
	IF (isnull(gTaskCnt),0,gTaskCnt) AS 阶段活跃任务数,
	avg_rank AS 阶段任务平均互动率,
	avgperson AS 平均互动_人,
	(SELECT GROUP_CONCAT(cName) FROM yxxs_basecurriculum WHERE FIND_IN_SET(cId, curriculumList)) AS 任务学科
FROM
(
	select 
		tt.*,
		b.schoolId schoolId,
		bw.openId,
		ac.cId, cName, ad.dId, dName, ap.pId, pName,
		so.name as schoolName,
		sum(round(rank*stucnt))/ count(a.topicDiscussionId) AS avgperson,
		count(distinct a.topicDiscussionId) as all_taskCnt,
		group_concat(distinct b.classid) AS classList,
		group_concat(distinct if(b.schoolid>0,o.name,null)) as classNames,
		count(distinct classId) AS classCnt,
		max(a.createTime) lastTime,
		min(a.createTime) firstTime,
		avg(if(a.createTime >=@var_taskDay,rank,null)) AS avg_rank,
		round(sum(if(a.createTime >=@var_taskDay,IF(rank > 0.5, 1, 0),0))/count(distinct classid)) AS gTaskCnt,
		round(sum(if(a.createTime >=@var_taskDay,IF(rank <= 0.3, 1, 0),0))/count(distinct classid)) AS lTaskCnt,
		round(sum(if(a.createTime >=@var_taskDay,IF (rank > 0.3 AND rank <= 0.5, 1, 0),0))/count(distinct classid)) AS mTaskCnt,
		round(sum(if(a.createTime >=@var_taskDay,1,0))/count(distinct classid)) AS taskCnt,
		GROUP_CONCAT(DISTINCT(if(a.createTime >=@var_taskDay,v_ti.curriculumId,null))) AS curriculumList,
		avg(if(a.createTime >=@init_startTime,rank,null)) AS a_avg_rank,
		round(sum(if(a.createTime >=@init_startTime,IF(rank > 0.5, 1, 0),0))/count(distinct classid)) AS a_gTaskCnt,
		round(sum(if(a.createTime >=@init_startTime,IF(rank <= 0.3, 1, 0),0))/count(distinct classid)) AS a_lTaskCnt,
		round(sum(if(a.createTime >=@init_startTime,IF (rank > 0.3 AND rank <= 0.5, 1, 0),0))/count(distinct classid)) AS a_mTaskCnt,
		round(sum(if(a.createTime >=@init_startTime,1,0))/count(distinct classid)) AS a_taskCnt,
		GROUP_CONCAT(DISTINCT(if(a.createTime >=@init_startTime,v_ti.curriculumId,null))) AS a_curriculumList
	from view_yxxs_user_info tt 
	LEFT JOIN view_yxxs_user_class_school b on tt.userid = b.userid
	left join organization_ as o on b.classId=o.organizationId
	left join organization_ as so on b.schoolId=so.organizationId
	LEFT JOIN yxxs_topicdiscussion a on tt.userid = a.userid
	LEFT JOIN view_yxxs_topic_info as v_ti force index(index_view_yxxs_topic_info) ON a.topicdiscussionid = v_ti.topicdiscussionid
	LEFT JOIN yxxs_topicdiscussionrank_ext AS rank ON a.topicdiscussionid = rank.topicdiscussionid
	LEFT JOIN yxxs_address AS address ON b.schoolId = address.classPK AND classNameId = 56702
	LEFT JOIN yxxs_province AS ap ON address.province = ap.pid
	LEFT JOIN yxxs_city AS ac ON address.city = ac.cid
	LEFT JOIN yxxs_district AS ad ON address.district = ad.did
	LEFT JOIN yxxsweixin_bindweixin bw ON bw.userId = tt.userId
	where usertype = 3
		AND emailAddress NOT LIKE '%@iyxxs.com'
		AND emailAddress NOT LIKE '%@youxueup.com'
	GROUP BY tt.userId
) as u 
left join
(
	select teacherId as userId,count(studentId) as cnt_stu
	from view_yxxs_teacher_student_relation group by teacherId
) as stu on u.userId = stu.userId
