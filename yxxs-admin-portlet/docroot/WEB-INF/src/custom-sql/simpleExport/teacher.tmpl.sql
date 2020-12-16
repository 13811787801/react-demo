SELECT
	CONCAT(u.lastName, u.firstName) AS 教师姓名,
	u.emailAddress AS 账号,
	u.createDate AS 注册日期,
	(
		SELECT
			count(topicdiscussionid)
		FROM
			yxxs_topicdiscussion
		WHERE
			userId = u.userid
	) AS 任务总数,
	(
		SELECT
			min(createTime)
		FROM
			yxxs_topicdiscussion
		WHERE
			userId = u.userid
	) AS 首个任务时间,
	classNames AS 班级,
	stuCount AS 学生总数,
	(
		SELECT
			NAME
		FROM
			organization_
		WHERE
			organizationId = ccc.parentOrganizationId
	) AS 学校,
	(
		SELECT
			GROUP_CONCAT(cName)
		FROM
			yxxs_basecurriculum
		WHERE
			cId IN (1, 2, 3)
		AND FIND_IN_SET(cId, curriculumList)
	) AS 学科
FROM
	(
		SELECT
			*
		FROM
			user_
		WHERE
			userId IN (
				SELECT
					userId
				FROM
					yxxs_user_ext
				WHERE
					type_ = 3
			)
		AND userId NOT IN (
			SELECT
				userId
			FROM
				user_
			WHERE
				emailAddress LIKE '%@iyxxs.com'
			OR emailAddress LIKE '%@youxueup.com'
		)
	) AS u
LEFT JOIN (
	SELECT
		c.userId,
		group_concat(classId) AS classList,
		count(classId) AS classCnt,
		(
			SELECT
				parentOrganizationId
			FROM
				organization_
			WHERE
				organizationId = max(classId)
			LIMIT 1
		) AS parentOrganizationId
	FROM
		(
			SELECT
				u.userId,
				uo.organizationId AS classId
			FROM
				user_ u
			LEFT JOIN yxxs_user_ext ue ON u.userId = ue.userId,
			users_orgs uo
		WHERE
			u.userId = uo.userId
		AND ue.type_ = 3
		AND ue.userId NOT IN (
			SELECT
				userId
			FROM
				user_
			WHERE
				emailAddress LIKE '%@iyxxs.com'
			OR emailAddress LIKE '%@youxueup.com'
		)
		AND uo.organizationId IN (
			SELECT
				c.organizationId
			FROM
				yxxs_organization_ext c
			WHERE
				c.orgType = 1025
		)
		) AS c
	GROUP BY
		c.userId
) AS ccc ON ccc.userId = u.userId
LEFT JOIN yxxsweixin_bindweixin bw ON bw.userId = u.userId
LEFT JOIN (
	SELECT
		userId,
		GROUP_CONCAT(DISTINCT(NAME)) AS classNames,
		count(DISTINCT(stuId)) AS stuCount
	FROM
		(
			SELECT
				u.userId,
				uo.organizationId AS classId,
				o.NAME,
				cm.userId AS stuId
			FROM
				user_ u
			LEFT JOIN yxxs_user_ext ue ON u.userId = ue.userId,
			users_orgs uo
		LEFT JOIN organization_ o ON o.organizationId = uo.organizationId
		LEFT JOIN yxxs_class_members cm ON cm.classId = uo.organizationId
		WHERE
			u.userId = uo.userId
		AND ue.type_ = 3
		AND cm.endDate IS NULL
		AND cm.roleId = 14214
		AND cm.flag = 1051
		AND ue.userId NOT IN (
			SELECT
				userId
			FROM
				user_
			WHERE
				emailAddress LIKE '%@iyxxs.com'
			OR emailAddress LIKE '%@youxueup.com'
		)
		AND uo.organizationId IN (
			SELECT
				c.organizationId
			FROM
				yxxs_organization_ext c
			WHERE
				c.orgType = 1025
		)
		) AS u
	GROUP BY
		u.userId
) AS ct ON ct.userId = u.userId
LEFT JOIN (
	SELECT
		t.userId,
		avg(rank) AS avg_rank,
		sum(IF(rank > 0.5, 1, 0)) AS gTaskCnt,
		sum(IF(rank <= 0.3, 1, 0)) AS lTaskCnt,
		sum(

			IF (rank > 0.3 AND rank <= 0.5, 1, 0)
		) AS mTaskCnt,
		count(t.topicdiscussionid) AS taskCnt,
		GROUP_CONCAT(DISTINCT(curriculumId)) AS curriculumList,
		GROUP_CONCAT(t.topicdiscussionid) AS taskList
	FROM
		(
			SELECT
				a.topicdiscussionid,
				a.userId,
				rank.rank,

			IF (
				a.curriculumId = 0,
				df.curriculumId,
				a.curriculumId
			) AS curriculumId
			FROM
				yxxs_topicdiscussion AS a
			LEFT JOIN yxxs_dlfileentry df ON a.fileEntryId = df.fileEntryId
			LEFT JOIN yxxs_topicdiscussionrank_ext AS rank ON a.topicdiscussionid = rank.topicdiscussionid
		) AS t
	GROUP BY
		userId
) AS tt ON u.userId = tt.userId
left join yxxs_org_stages as os on ccc.parentOrganizationId = os.organizationId
where os.stageLevel = 1007 and bw.openId is not null