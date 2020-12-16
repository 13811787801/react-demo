select 
学校名称
<%for(var i=0;i>-30;i--){%>
<%
var today = new Date(parseInt(new Date()/1000/60/60/24)*1000*24*60*60);
var dd = new Date(today.getTime() + i*1000*24*60*60).getTime();
%>
,case when 日期='<%=new Date(dd).Format("yyyy-MM-dd")%>' then 总数 else 0 end as '<%=new Date(dd).Format("yyyy年MM月dd日")%>'
<%}%>
from
(
<%@ include file="classpath:custom-sql/simpleExport/taskDaySchoolStatus.tmpl.sql" %>
) as tt
group by 学校名称
