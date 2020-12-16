create table yxxs_action (
	id_ bigint(20) not null primary key,
	appKey varchar(75) null,
	createId bigint(20),
	flag int(11),
	title varchar(75) null,
	describe_ varchar(75) null,
	content varchar(75) null,
	url varchar(75) null,
	createDate datetime null,
	endDate datetime null,
	startDate datetime null
);

create table yxxs_action_deal_log (
	id_ bigint(20) not null primary key,
	actionId bigint(20),
	createId bigint(20),
	type_ int(11),
	content varchar(75) null,
	createDate datetime null
);

create table yxxs_action_user (
	id_ bigint(20) not null primary key,
	actionId bigint(20),
	createId bigint(20),
	userId bigint(20),
	userType int(11),
	isLogged int(11),
	openId varchar(75) null,
	createDate datetime null,
	loginDate datetime null,
	rName varchar(75) null,
	rMobile varchar(75) null,
	rSchool varchar(75) null
);

create table yxxs_wechat_wall (
	id_ bigint(20) not null primary key,
	actionId bigint(20),
	userId bigint(20),
	showFlag int(11),
	content varchar(75) null,
	openId varchar(75) null,
	appKey varchar(75) null,
	createDate datetime null,
	msgType int(11),
	toId bigint(20),
	contentType int(11)
);