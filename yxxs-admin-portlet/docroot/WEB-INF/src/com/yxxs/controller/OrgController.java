package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrgController extends BaseWwwController {
	
	@RequestMapping("admin/org/schoolList") 
	public String schoolList(
			Model model
			){
		
		return "org/schoolList";
	}
	@RequestMapping("admin/org/schoolForm") 
	public String schoolEdit(
			Model model
			){
		
		return "org/schoolEdit";
	}
	
	@RequestMapping("admin/org/memberSchoolForm") 
	public String memberSchoolEdit(
			Model model
			){
		
		return "org/memberSchoolEdit";
	}
	@RequestMapping("admin/org/classList") 
	public String classList(
			@RequestParam(value="schoolId") Long schoolId,
			Model model
			){
		
		return "org/classList";
	}
	@RequestMapping("admin/org/classForm") 
	public String classEdit(
			Model model
			){
		
		return "org/classEdit";
	}
	@RequestMapping("admin/org/classMemberForm") 
	public String classMemberEdit(
			@RequestParam(value="classId") Long classId,
			Model model
			){
		
		return "org/classMemberEdit";
	}
	@RequestMapping("admin/org/classMemberImport") 
	public String classMemberAdd(
			@RequestParam(value="classId") Long classId,
			Model model
			){
		
		return "org/classMemberAdd";
	}

	@RequestMapping("admin/org/classStageList") 
	public String classStageList(
			@RequestParam(value="classId") Long classId,
			Model model
			){
		
		return "org/classStageList";
	}
	@RequestMapping("admin/org/classStageForm") 
	public String classStageEdit(
			@RequestParam(value="classStageId") Long classStageId,
			Model model
			){
		
		return "org/classStageEdit";
	}

	@RequestMapping("admin/org/userClassList") 
	public String userList(
			@RequestParam(value="userId",required=false) String userId,
			Model model
			){
		
		return "org/userClassList";
	}

	
	@RequestMapping("admin/org/applyJoinClassMsgList")
	public String joinClassMsgList(
			Model model
			){
		
		return "org/applyJoinClassMsgList";
	}


	
	@RequestMapping("admin/org/demonstrationClassList")
	public String demonstrationClassList (
			Model model
			){
		
		return "org/demonstrationClassList";
	}
	
	@RequestMapping("admin/org/researchClassList")
	public String researchClassList (
			Model model
			){
		
		return "org/researchClassList";
	}
	
	@RequestMapping("admin/org/researchClassForm")
	public String researchClassForm (
			Model model
			){
		
		return "org/researchClassEdit";
	}
	
	@RequestMapping("admin/org/researchClassMemberList")
	public String researchClassMemberList (
			Model model
			){
		
		return "org/researchClassMemberList";
	}
	
	@RequestMapping("admin/org/addResearchClassMember")
	public String addResearchClassMember (
			Model model
			){
		
		return "org/addResearchClassMember";
	}
	
	@RequestMapping("admin/org/watchClassApplyList")
	public String watchClassApplyList (
			Model model
			){
		
		return "org/watchClassApplyList";
	}
	
	@RequestMapping("admin/org/schoolTeachersList")
	public String schoolTeachersList (
			Model model
			){
		
		return "org/schoolTeachersList";
	}
	
	@RequestMapping("admin/org/schoolTeachingLeaderList")
	public String schoolTeachingLeaderList (
			Model model
			){
		
		return "org/schoolTeachingLeaderList";
	}
	
	@RequestMapping("admin/org/authedSchoolLeaderList")
	public String authedSchoolLeaderList (
			Model model
			){
		
		return "org/authedSchoolLeaderList";
	}
}