create index IX_D99AC833 on yxxs_action (appKey);

create index IX_E972E60 on yxxs_action_deal_log (actionId);
create index IX_F1D27E26 on yxxs_action_deal_log (createId);

create index IX_511E8C3A on yxxs_action_user (actionId);
create index IX_EC42740E on yxxs_action_user (openId);
create index IX_98956F33 on yxxs_action_user (openId, actionId);
create index IX_FA74910F on yxxs_action_user (userId);
create index IX_8D6BFEF4 on yxxs_action_user (userId, actionId);

create index IX_64719CC9 on yxxs_wechat_wall (actionId);
create index IX_5D830996 on yxxs_wechat_wall (appKey);
create index IX_FAEE5C5D on yxxs_wechat_wall (openId);
create index IX_C21280C2 on yxxs_wechat_wall (openId, actionId);
create index IX_1FCE97CF on yxxs_wechat_wall (openId, appKey);
create index IX_920795E on yxxs_wechat_wall (userId);
create index IX_B6E91083 on yxxs_wechat_wall (userId, actionId);
create index IX_30D823D0 on yxxs_wechat_wall (userId, appKey);