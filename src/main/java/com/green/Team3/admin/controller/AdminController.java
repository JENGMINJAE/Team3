package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.member.service.MemberService;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminService adminService;

    @Resource(name = "memberService")
    private MemberService memberService;

    // 관리자 클릭 시 페이지 이동
    @GetMapping("/notice")
    public String notice(){
        return "content/admin/admin_list";
    }

    // 강사 관리 페이지 이동
    @GetMapping("/goAdminTeacher")
    public String goAdminTeacher(Model model){
        List<TeacherVO> list = adminService.selectTeachers();
        System.out.println(list);
        model.addAttribute("teacherList", list); // 강사 목록 조회
        return "content/admin/admin_teacher";
    }

    // 회원 관리 페이지 이동
    @GetMapping("/goMemberList")
    public String memberList(Model model){
        // 전체 회원 목록 조회
        model.addAttribute("memberList", memberService.selectMembers());
        model.addAttribute("rollList", adminService.rollList());
        return "content/admin/member_list";
    }

//    // 인적 사항 보기
    @ResponseBody
    @PostMapping("/memberDetail")
    public void memberDetail(@RequestBody MemberVO memberVO){
        //MemberVO vo = memberService.memberDetail(memberVO);
        //System.out.println(vo);
        //return vo;
    }

    // 강사 권한 수정 (학생 -> 강사)
    @GetMapping("/updateTeacher")
    public String updateTeacher(TeacherVO teacherVO){
        adminService.updateTeacher(teacherVO);
        return "redirect:/admin/goAdminTeacher?teacherNum= " + teacherVO.getTeacherNum();
    }

    // 학급 생성 페이지 이동
    @GetMapping("/makeClassForm")
    public String makeClassForm(){
        return "content/admin/make_class_form";
    }

    // 학급 생성 버튼 클릭 시 실행 메소드
    @PostMapping("/makeClass")
    public String makeClass(){
        return "redirect:/admin/makeClassForm";
    }
    // 강사 정보 상세 조회
    @ResponseBody
    @PostMapping("/selectTeacher")
    public TeacherVO detailTeacher(@RequestBody TeacherVO teacherVO){
        TeacherVO teacherInfo = adminService.detailTeacher(teacherVO);
        return teacherInfo;
    }

    // 근무 상태 변경
    @PostMapping("/changeAttendance")
    public String changeAttendance(TeacherVO teacherVO){
        adminService.changeAttendance(teacherVO);
        return "redirect:/admin/goAdminTeacher";
    }
}
