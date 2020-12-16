package com.yxxs.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sun.management.counter.Variability;

import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.maker.SqlMaker;
import com.yxxs.common.script.EjsEngine;
import com.yxxs.common.util.ConversUtil;
import com.yxxs.common.util.DateUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.common.util.SqlMakerUtil;
import com.yxxs.common.util.SqlMakerUtil.SimpleSqlCommand;
import com.yxxs.common.util.SqlParser;
import com.yxxs.common.util.SqlParser.Position;
import com.yxxs.common.util.SqlParser.SelectSqlObject;
import com.yxxs.common.util.TmplResourceUtil;
import com.yxxs.dao.CommonStatisticDao;
import com.yxxs.dao.mapper.BaseStatsMapper;
import com.yxxs.util.PropsConfig;

@Controller
public class StatisticController extends BaseWwwController {

	@Autowired
	CommonStatisticDao commonStatisticsDao_;

	
	@RequestMapping("admin/statistic/topic") 
	public String topic(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="pageSize",defaultValue="20") Integer pageSize,
			@RequestParam(value="columnHeaderIndexes",required=false) String columnHeaderIndexes,
			Model model
			) throws RecognitionException{

		String cnd = " "+
		" a.createTime >= '2015-01-01' "+
		" ";
		List<Object> params = new ArrayList<Object>();
		
		StatisticViewMapping sv = topicMapping();
		if(null == columnHeaderIndexes || "".equals(columnHeaderIndexes)){
			columnHeaderIndexes = getColumnHeaderIndexes(sv, "ID,任务名称,班级数,发布班级学生数,参与率,任务附件,作者,学科,年级,学校,创建时间,有说明");
		}
		
		commonDealMapper(sv, page, pageSize, cnd, params, columnHeaderIndexes, null, model);
		
		return "statistic/topic";
	}
	
	//任务统计
	@RequestMapping("admin/statistic/taskStatistics") 
	public String taskStatistics(
			@RequestParam(value="province",required=false) Integer province,
			@RequestParam(value="city",required=false) Integer city,
			@RequestParam(value="district",required=false) Integer district,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="school",required=false) String school,
			@RequestParam(value="startTime",defaultValue="2017-01-01") Date startTime,
			@RequestParam(value="interactRate",defaultValue="0") Integer interactRate,
			@RequestParam(value="exportExcel",defaultValue="false") Boolean exportExcel,
			@RequestParam(value="order",required=false,defaultValue="a.createTime desc") String order,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="pageSize",defaultValue="50") Integer pageSize,
			Model model,
			HttpServletResponse response
			) throws IOException, RecognitionException{
		
		List<Object> params = new ArrayList<Object>();
		
		String cnd = " 1=1 ";
		if(null != province){
			cnd += " and ap.pId = ? ";
			params.add(province);
		}
		
		if(null != city){
			cnd += " and ac.cId = ? ";
			params.add(city);
		}
		
		if(null != district){
			cnd += " and ad.dId = ? ";
			params.add(district);
		}
		
		if(null != name && !("".equals(name))){
			cnd += " and concat(b.lastName,b.firstName) like concat('%',?,'%') ";
			params.add(name);
		}
		
		if(null != school && !("".equals(school))){
			cnd += " and so.name like concat('%',?,'%') ";
			params.add(school);
		}
		
		if(null != startTime){
			cnd += " and a.createTime >= ? ";
			params.add(startTime);
		}
		
		if(interactRate != 0){
			if(interactRate == 1)
				  cnd += " and rank.rank <=0.3 "; 
			if(interactRate == 2)
			      cnd += " and rank.rank > 0.3  and  0.5 => rank.rank ";
			if(interactRate == 3)
				  cnd += " and rank.rank > 0.5 ";
		}
		
		StatisticViewMapping sv = taskStatisticsMapping(topicMapping());
		
		String columnHeaderIndexes = "";
		
		if(exportExcel){
			String fName = "任务发布互动情况";
			if(null != startTime){
				fName += "-" +DateUtil.getFormatDate(startTime,"yyyy-MM-dd")+"起";
			}
			
			columnHeaderIndexes = getColumnHeaderIndexes(sv, "ID,任务名称,任务说明,任务附件,电脑链接,互动统计,参与率,创建时间,发布人,发布班级,发布班级学生数,学科,年级,学校名称,地区");
			exportExcel(fName,response,sv, null, null, cnd, params, columnHeaderIndexes, " a.createTime desc ", model);
			return null;
		}
		
		columnHeaderIndexes = getColumnHeaderIndexes(sv, "ID,任务名称,互动统计,互动率,创建时间,发布人,发布班级,学校名称,地区,电脑链接");
		
		commonDealMapper(sv, page, pageSize, cnd, params, columnHeaderIndexes, " "+order+" ", model);
				
		return "statistic/taskStatistics";
	}
		
	//教师统计
	@RequestMapping("admin/statistic/teacherStatistics") 
	public String teacherStatistics(
			@RequestParam(value="province",required=false) Integer province,
			@RequestParam(value="city",required=false) Integer city,
			@RequestParam(value="district",required=false) Integer district,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="school",required=false) String school,
			@RequestParam(value="startTime",defaultValue="2017-01-01") Date startTime,
			@RequestParam(value="exportExcel",defaultValue="false") Boolean exportExcel,
			@RequestParam(value="order",required=false,defaultValue="column_5") String order,//a_taskCnt desc
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="pageSize",defaultValue="50") Integer pageSize,
			HttpServletResponse response,
			Model model
			) throws IOException, RecognitionException{
		List<Object> params = new ArrayList<Object>();
		
		String cnd = " 1=1 ";
		if(null != province){
			cnd += " and pId = ? ";
			params.add(province);
		}
		
		if(null != city){
			cnd += " and cId = ? ";
			params.add(city);
		}
		
		if(null != district){
			cnd += " and dId = ? ";
			params.add(district);
		}
		
		if(null != name && !("".equals(name))){
			cnd += " and CONCAT(u.lastName,u.firstName) like concat('%',?,'%') ";
			params.add(name);
		}
		
		if(null != school && !("".equals(school))){
			cnd += " and schoolName like concat('%',?,'%') ";
			params.add(school);
		}
		
		StatisticViewMapping sv = teacherStatisticsMapping(teacherClassMapping());
		
		dealSQL(startTime,sv);
		
		String columnHeaderIndexes = "";
		
		if(exportExcel){
			String fName = "教师任务发布情况";
			if(null != startTime){
				fName += "-" +DateUtil.getFormatDate(startTime,"yyyy-MM-dd") + "起";
			}
			
			columnHeaderIndexes = getColumnHeaderIndexes(sv,
					"教师姓名,任务总数_16_01_01起,阶段任务数量,活跃任务数_16_01_01起,阶段活跃任务数,任教学科,"+
					"最后发任务时间,所在班级,学校,地区,低互动率任务数_16_01_01起,阶段低互动率任务数,中互动率任务数_16_01_01起,阶段中互动率任务数,任务平均互动率,阶段任务平均互动率");
			
			exportExcel(fName,response,sv, null, null, cnd, params, columnHeaderIndexes, " a_taskCnt desc ", model);
			return null;
		}
		
		columnHeaderIndexes = getColumnHeaderIndexes(sv, "账号,任务总数_16_01_01起,阶段任务数量,活跃任务数_16_01_01起,阶段活跃任务数,任教学科,最后发任务时间,最近登陆时间,所在班级,学校,地区");
		
		commonDealMapper(sv, page, pageSize, cnd, params, columnHeaderIndexes, " "+order+" desc ", model);
		
		return "statistic/teacherStatistics";
	}
	
	
	//学校统计
	@RequestMapping("admin/statistic/schoolStatistics") 
	public String schoolStatistics(
			@RequestParam(value="province",required=false) Integer province,
			@RequestParam(value="city",required=false) Integer city,
			@RequestParam(value="district",required=false) Integer district,
			@RequestParam(value="school",required=false) String school,
			@RequestParam(value="startTime",defaultValue="2017-01-01") Date startTime,
			@RequestParam(value="exportExcel",defaultValue="false") Boolean exportExcel,
			@RequestParam(value="order",required=false,defaultValue="column_16") String order,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="pageSize",defaultValue="50") Integer pageSize,
			HttpServletResponse response,
			Model model
			) throws IOException, RecognitionException{
		List<Object> params = new ArrayList<Object>();
		
		String cnd = " 1=1 ";
		if(null != province){
			cnd += " and ap.pId = ? ";
			params.add(province);
		}
		
		if(null != city){
			cnd += " and ac.cId = ? ";
			params.add(city);
		}
		
		if(null != district){
			cnd += " and ad.dId = ? ";
			params.add(district);
		}
		
		
		if(null != school && !("".equals(school))){
			cnd += " and o.name like concat('%',?,'%') ";
			params.add(school);
		}

		String sqlStr = TmplResourceUtil.getTmplResource(StatisticController.class, "classpath:custom-sql/statistic/school.tmpl.sql");
		
		StatisticViewMapping sv = parseSqlMapping(sqlStr);
				
		dealSQL(startTime, sv);
		String columnHeaderIndexes = "";
		
		if(exportExcel){
			String fName = "学校任务发布情况";
			if(null != startTime){
				fName += "-" +DateUtil.getFormatDate(startTime,"yyyy-MM-dd")+ "起";
			}
			
			columnHeaderIndexes = getColumnHeaderIndexes(sv,
					"学校,地区,现有教师总数,现有教师绑定总数,发布任务教师数_16_01_01起,阶段发任务教师数,任务数_16_01_01起,阶段任务数," +
					"高互动率任务数_16_01_01起,阶段高互动率任务数,班级总数,发任务班级数_16_01_01起,阶段发任务班级数,学生总数,学生绑定总数,阶段登录学生数,"+
					"上传作业学生数_16_01_01起,阶段上传作业学生数,任务平均互动率_16_01_01起,阶段任务平均互动率,"+
					"低互动率任务数_16_01_01起,阶段低互动率任务数,中互动率任务数_16_01_01起,阶段中互动率任务数,"+
					"最后建班级时间,运营账号");
			
			exportExcel(fName,response,sv, null, null, cnd, params, columnHeaderIndexes, " all_task_cnt desc ", model);
			return null;
		}
		
		columnHeaderIndexes = getColumnHeaderIndexes(sv, "学校,地区,现有教师总数,现有教师绑定总数,发布任务教师数_16_01_01起,阶段发任务教师数,任务数_16_01_01起,阶段任务数," +
				"高互动率任务数_16_01_01起,阶段高互动率任务数,班级总数,发任务班级数_16_01_01起,阶段发任务班级数,学生总数,学生绑定总数,阶段登录学生数,最后建班级时间,运营账号");
		
		commonDealMapper(sv, page, pageSize, cnd, params, columnHeaderIndexes, " "+order+" desc ", model);
		
		return "statistic/schoolStatistics";
	}

	private void dealSQL(Date startTime, StatisticViewMapping sv) {
		String initStartTime = "'2016-01-01'";
		String StartTimeStr = "'2017-01-01'";
		if(null != startTime){
			StartTimeStr = "'"+DateUtil.getFormatDate(startTime,"yyyy-MM-dd")+"'";
		}
		
		sv.SQL = sv.SQL.replace("@var_taskDay", StartTimeStr);
		sv.SQL = sv.SQL.replace("@init_startTime", initStartTime);
		
		for(String[] arr : sv.columns){
			if(arr[0].indexOf("@var_taskDay") != -1){
				arr[0] = arr[0].replace("@var_taskDay", StartTimeStr);
			}
			if(arr[0].indexOf("@init_startTime") != -1){
				arr[0] = arr[0].replace("@var_taskDay", initStartTime);
			}
		}
	}
	
	private static StatisticViewMapping teacherStatisticsMapping(
			StatisticViewMapping sv) {
		List<String[]> list = sv.columns;
		
		list.add(new String[]{"concat('姓名:',CONCAT(u.lastName,u.firstName),'<br>账号：',u.emailAddress)", "账号"});
		list.add(new String[]{"if(isnull(a_taskCnt),0,a_taskCnt)", "任务总数_16_01_01起"});
		list.add(new String[]{"if(isnull(a_gTaskCnt),0,a_gTaskCnt)", "活跃任务数_16_01_01起"});
		list.add(new String[]{"(select GROUP_CONCAT(cName) from yxxs_basecurriculum where FIND_IN_SET(cId,a_curriculumList))", "任教学科"});
		list.add(new String[]{"concat(pName,'-',cName,'-',dName)","地区"});
		list.add(new String[]{"a_lTaskCnt","低互动率任务数_16_01_01起"});
		list.add(new String[]{"lTaskCnt","阶段低互动率任务数"});
		list.add(new String[]{"a_mTaskCnt","中互动率任务数_16_01_01起"});
		list.add(new String[]{"mTaskCnt","阶段中互动率任务数"});
		list.add(new String[]{"a_avg_rank", "任务平均互动率"});
		
		sv.SQL += 
		"  left join organization_ org on org.organizationId = schoolId ";
		
		return sv;
	}

	private static StatisticViewMapping taskStatisticsMapping(StatisticViewMapping sv){
		List<String[]> list = sv.columns;
		list.add(new String[]{"concat(tc.viewUserNum,'查看,',tc.downloadUserNum,'下载,',tc.discussionUserNum,'讨论,',tc.homeworkNum,'上传')","互动统计"});
		list.add(new String[]{"concat('作者：',b.lastName,b.firstName,',账号：',b.emailAddress)","发布人"});//
		list.add(new String[]{"(select group_concat(NAME) from organization_ where find_in_set(organizationId,classList))","发布班级"});
		list.add(new String[]{"concat(ap.pName,'-',ac.cName,'-',ad.dName)","地区"});
		list.add(new String[]{"so.name","学校名称"});
		list.add(new String[]{"CONCAT('http://"+PropsConfig.SERVER_HOST+"/yxxs-main-portlet/web/topicdiscussion',"+
				"'_',a.topicDiscussionId)","电脑链接"});
		
		//list.add(new String[]{"so.organizationId","学校ID"});
		
		return sv;
	}
	
	private static StatisticViewMapping topicMapping() throws RecognitionException{
		String sqlStr = TmplResourceUtil.getTmplResource(StatisticController.class, "classpath:custom-sql/statistic/topic.tmpl.sql");
		return parseSqlMapping(sqlStr);
	}
	
	private static StatisticViewMapping parseSqlMapping(String sqlStr) throws RecognitionException{

		StatisticViewMapping sv = new StatisticViewMapping();

		List<String[]> columns = new ArrayList<String[]>();
		
		SelectSqlObject sql = new SelectSqlObject();
		sql = SqlParser.parseSql(sqlStr);
		
		String[] alias = sql.columns.keySet().toArray(new String[]{});
		for(int i=0;i<alias.length;i++){
			columns.add(new String[]{sql.columns.get(alias[i]), alias[i]});
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(sql.table);
		if(null != sql.where){
			sb.append("where").append(" ").append(sql.where);
			sb.append("\n");
		}
		if(null != sql.groupBy){
			sb.append("group by").append(" ").append(sql.groupBy);
			sb.append("\n");
		}
		if(null != sql.orderBy){
			sb.append("order by").append(" ").append(sql.orderBy);
			sb.append("\n");
		}
		String SQL =  sb.toString();
		
		sv.columns = columns;
		sv.SQL = SQL;
		
		return sv;
	}

	@RequestMapping("admin/statistic/teacher") 
	public String teacherClass(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="pageSize",defaultValue="20") Integer pageSize,
			@RequestParam(value="columnHeaderIndexes",required=false) String columnHeaderIndexes,
			Model model
			) throws RecognitionException{

		String cnd = " "+
		" u.userId > 0"+
		" ";
		List<Object> params = new ArrayList<Object>();

		
		StatisticViewMapping sv = teacherClassMapping();
		sv.SQL = sv.SQL.replace("@var_taskDay", "'2016-09-01'");
		if(null == columnHeaderIndexes || "".equals(columnHeaderIndexes)){
			columnHeaderIndexes = getColumnHeaderIndexes(sv, "教师姓名,阶段任务数量,任务总数,活跃任务,任务学科,最后发任务时间,学校,所在班级");
		}
		
		commonDealMapper(sv, page, pageSize, cnd, params, columnHeaderIndexes, null, model);
		
		return "statistic/teacherClass";
	}
	
	//@var_taskDay
	private static StatisticViewMapping teacherClassMapping() throws RecognitionException{
		String sqlStr = TmplResourceUtil.getTmplResource(StatisticController.class, "classpath:custom-sql/statistic/teacherClass.tmpl.sql");
		return parseSqlMapping(sqlStr);
	}
	
	
	
	
	
	//
	//
	/**
	 * 
	
	 * @Title: parseColumnMap
	
	 * @Description: 替换columns成为 [ 展示列名, column_i ];生成hash设置为 { DB列名 : column_i }
	
	 * @param:   
	
	 * @return:  
	
	 * @throws
	 */
	protected static Hashtable<String, String> parseColumnMap(List<String[]> columns){
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		for(int i=0;i<columns.size();i++){
			hash.put(columns.get(i)[0], "column_"+i);
			columns.set(i, new String[]{new String(columns.get(i)[1]),"column_"+i});
		}
		
		return hash;
	}
	
	
	
	
	private void exportExcel(String fileName, HttpServletResponse response,
			StatisticViewMapping sv, Integer page, Integer pageSize,
			String cnd, List<Object> params, String columnHeaderIndexes,String order,
			Model model) throws IOException {
		List<String[]> tempColumns = new ArrayList<String[]>(sv.columns);
		
		List<String[]> allColumns = new ArrayList<String[]>();
		
		if(null != columnHeaderIndexes && !("".equals(columnHeaderIndexes))){
			List<String> colList = Arrays.asList(columnHeaderIndexes.split(","));
			for(String col : colList){
				int tempCol = Integer.parseInt(col);
				allColumns.add(tempColumns.get(tempCol));
			}
		}
		
		final Hashtable<String, String> map = parseColumnMap(allColumns);
		
		PagerResult<List<Hashtable<String, Object>>> result = commonSearch(sv,
				(null == page? 1:page), (null == pageSize ? Integer.MAX_VALUE : pageSize), cnd, params, map, order);
		
		String fName = fileName;
		
        response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(fName+".xls", "UTF-8"));
        
        List<Hashtable<String, Object>> list = result.getObj();

        allColumns.add(0, new String[]{"序号", "rowNum"});
        for(int i=0;i<list.size();i++){
        	list.get(i).put("rowNum", (i+1));
        }
        
		ExcelController.exportExcel(allColumns,list,response.getOutputStream());
	}
	
	
	//通用设置页面属性, 参照statisticTableView.jsp
	private void commonDealMapper(final StatisticViewMapping sv, 
			int page, int pageSize,
			String cnd, List params, 
			String  columnHeaderIndexes,
			String order,
			Model model){

		List<String[]> allColumns = new ArrayList<String[]>(sv.columns);
		
		final Hashtable<String, String> map =  parseColumnMap(allColumns);
		
		List<String[]> columnsFinal = new ArrayList<String[]>();
		
		if(null != columnHeaderIndexes && !"".equals(columnHeaderIndexes)){
			List<String> colList = Arrays.asList(columnHeaderIndexes.split(","));

			for(String col : colList){
				for(String[] arr : allColumns){
					if(arr[1].equals("column_"+col)){
						columnsFinal.add(arr);
						break;
					}
				}
			}
		}
		
		PagerResult<List<Hashtable<String, Object>>> result = commonSearch(sv,
				page, pageSize, cnd, params, map, order);


		List<Hashtable<String, Object>> list = result.getObj();

		model.addAttribute("pi", (PagerInfo)result);
		model.addAttribute("list", list);
		model.addAttribute("columns", columnsFinal);

		model.addAttribute("allColumns", allColumns);
	}

	
	private PagerResult<List<Hashtable<String, Object>>> commonSearch(
			final StatisticViewMapping sv, int page, int pageSize, String cnd,
			List params, final Hashtable<String, String> map,String order) {
		PagerResult<List<Hashtable<String, Object>>> result = commonStatisticsDao_.search(new BaseStatsMapper() {
				
				public String getTableName() {
					return sv.SQL;
				}
				
				@Override
				protected Hashtable<String, String> getColumnFieldMap() {
					return  map;
				}
			}, 
			cnd, params.toArray(), order, new PagerInfo().setPageSize(pageSize).setPageNumber(page));
		return result;
	}
	
	
	private String getColumnHeaderIndexes(StatisticViewMapping sv, String columnHeaderNames){
		if(null == columnHeaderNames || "".equals(columnHeaderNames)){
			return null;
		}
		
		String columnHeaderIndexes = "";
		List<String> list = Arrays.asList(columnHeaderNames.split(","));
		for(String str : list){
			int temp = -1;
			for(int i=0; i<sv.columns.size(); i++){
				if(str.equals(sv.columns.get(i)[1])){
					temp = i;
					//break;  当旧列名与新加列名重复，选最后加的
				}
			}
			if(temp == -1){
				continue;
			}
			columnHeaderIndexes += ","+temp;
		}
		columnHeaderIndexes = columnHeaderIndexes.replaceFirst(",", "");
		return columnHeaderIndexes;
	}
	
	
	private static class StatisticViewMapping{
		public String SQL = null;
		public List<String[]> columns = new ArrayList<String[]>();
	}
	
	private static String toSql(StatisticViewMapping sv){
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT").append(" ");
		for(int i=0; i<sv.columns.size(); i++){
			if(i != 0){
				sb.append(",");
			}
			sb.append(sv.columns.get(i)[0]).append(" as ").append(sv.columns.get(i)[1]);
		}
		sb.append(" FROM ").append(sv.SQL);
		
		return sb.toString();
	}
	
	
	
	
	
	//export
	@RequestMapping("admin/export/exportExcel") 
	public String exportExcel(
			@RequestParam(value="paramHashJsonStr",defaultValue="{}") String paramHashJsonStr,
			@RequestParam(value="fileName") String fileName,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException, RecognitionException{
		
		String fName = fileName;

		String sqlStr = TmplResourceUtil.getTmplResource(StatisticController.class, "classpath:custom-sql/simpleExport/"+fileName+".tmpl.sql");

		try {
			sqlStr = new EjsEngine().setTmpl(sqlStr).render(new HashMap());
		} catch (ScriptException e) {
			//e.printStackTrace();
		}

		HashMap inputParams = JsonUtil.toObject(paramHashJsonStr, HashMap.class);
		
		SelectSqlObject sql = new SelectSqlObject();
		sql = SqlParser.parseSql(sqlStr);
		List<String> columns = Arrays.asList(sql.columns.keySet().toArray(new String[0]));
		
		SimpleSqlCommand  sc = SqlMakerUtil.makeCommand(sqlStr, inputParams);

		String columnHeaderIndexes = ConversUtil.strFormStrList(columns);

		StatisticViewMapping sv = parseSqlMapping(sqlStr);
		
		columnHeaderIndexes = getColumnHeaderIndexes(sv, columnHeaderIndexes);
		exportExcel(fName,response,sv, null, null, null, sc.params, columnHeaderIndexes, null, model);
		return null;
	}
	

	@RequestMapping("admin/export/simpleExport") 
	public String simpleExport(
			Model model
			) throws RecognitionException{

		return "export/simpleExport";
	}
	
	
	
	public static void main(String[] args) {
	}
}