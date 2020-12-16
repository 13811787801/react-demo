SELECT
	a.topicdiscussionid AS ID,
	a.title AS 任务名称,
	concat(b.lastName, b.firstName) AS 作者,
	b.emailAddress AS 作者账号,
	a.createTime AS 创建时间,
	(
		length(classList) - length(REPLACE(classList, ',', '')) + 1
	) AS 班级数,
	(
		length(stuList) - length(REPLACE(stuList, ',', '')) + 1
	) AS 发布班级学生数,
	tc.viewUserNum AS 浏览人数,
	tc.downloadUserNum AS 下载人数,
	tc.discussionUserNum AS 发言人数,
	tc.discussionNum AS 发言数量,
	rank.rank AS 参与率,
	(
		SELECT count(DISTINCT v_tua.userId)
		FROM view_yxxs_topic_user_action v_tua
		WHERE v_tua.topicDiscussionId = a.topicdiscussionid AND type = 'c'
	) AS 评论人数,
	v_tci.commentCnt AS 评论数量,
	tc.homeWorkUserNum AS 上传人数,
	v_tci.appraiseCnt AS 互评数量,
	v_tci.viewLastTime AS 最晚浏览,
	v_tci.downloadLastTime AS 最晚下载,
	v_tci.speachLastTime AS 最晚发言,
	v_tci.finishmentLastTime AS 最晚上传,
	v_tci.commentLastTime AS 最晚评论,
	v_tci.appraiseLastTime AS 最晚打分,

	v_ti.schoolId AS 学校ID,
	so.NAME AS 学校,
	v_ti.fileTitle AS 任务附件,
	v_ti.fileExt AS 附件格式,
	bc.cName AS 学科,
	concat(v_ti.grade, '年级') AS 年级,
	a.content AS 任务说明,
IF (LENGTH(a.content) = 0,'无','有') AS 有说明,
IF (a.terminalType = 10,'微信',IF (a.terminalType = 1,'网站','')) AS 任务来源

FROM
yxxs_topicdiscussion a 
left join view_yxxs_topic_counter_info as v_tci on a.topicdiscussionid=v_tci.topicdiscussionid
LEFT JOIN yxxs_topicdiscussionrank_ext AS rank ON a.topicdiscussionid = rank.topicdiscussionid
LEFT JOIN view_yxxs_topic_info AS v_ti ON v_ti.topicdiscussionid = a.topicdiscussionid
LEFT JOIN organization_ AS so ON v_ti.schoolid = so.organizationId
LEFT JOIN user_ b ON a.userId = b.userId
LEFT JOIN yxxs_topicdiscussioncounter tc ON a.topicDiscussionId = tc.topicDiscussionId AND tc.deliveryToGroupId = 0
left join yxxs_basecurriculum bc on bc.cId = v_ti.curriculumId
left join yxxs_address as ya on so.organizationId = ya.classPK and ya.classNameId=56702
left join yxxs_province as ap on ya.province = ap.pid
left join yxxs_city as ac on ya.city = ac.cid
left join yxxs_district as ad on ya.district = ad.did
