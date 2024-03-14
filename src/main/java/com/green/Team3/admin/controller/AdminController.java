package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.cls.service.ClsService;
import com.green.Team3.cls.vo.ClsVO;
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

    @Resource(name = "clsService")
    private ClsService clsService;

    // 관리자 클릭 시 페이지 이동
    @GetMapping("/notice")
    public String notice(){
        return "content/admin/admin_list";
    }

    // 강사 관리 페이지 이동
    @GetMapping("/goAdminTeacher")
    public String goAdminTeacher(Model model){
        List<TeacherVO> list = adminService.selectTeachers();
        model.addAttribute("teacherList", list); // 강사 목록 조회
        return "content/admin/admin_teacher";
    }

    // 회원 관리 페이지 이동
    @GetMapping("/goMemberList")
    public String memberList(Model model, @RequestParam(name = "memberId", required = false, defaultValue = "")String updateMemberId){
        // 전체 회원 목록 조회
        model.addAttribute("memberList", memberService.selectMembers());
        // MEMBER_ROLL 목록 조회 (관리, 강사, 회원)
        model.addAttribute("rollList", adminService.rollList());
        model.addAttribute("updateMemberId", updateMemberId);
        return "content/admin/member_list";
    }

    // 인적 사항 보기 (모달)
    @ResponseBody
    @RequestMapping("/memberDetail")
    public MemberVO memberDetail(@RequestBody MemberVO memberVO){
        MemberVO vo = memberService.memberDetail(memberVO);
        return vo;
    }

    // 인적 사항 변경 쿼리 실행(변경 버튼 클릭 시)
    @PostMapping("/changePersonalInfo")
    public String changePersonalInfo(MemberVO memberVO){
        adminService.changePersonalInfo(memberVO);
        return "redirect:/admin/goMemberList?memberId=" + memberVO.getMemberId();
    }

    // 회원 권한 수정 (memberRoll)
    @ResponseBody
    @PostMapping("/updateRoll")
    public MemberVO updateRoll(@RequestBody MemberVO memberVO){
        adminService.updateRoll(memberVO);
         return memberVO;
    }
//    ----------------------- 완료 ---------------------------
    // 해당 회원의 수강 목록 페이지 이동 (모달)
    @ResponseBody
    @PostMapping("/showClass")
    public List<ClsVO> showClass(@RequestBody MemberVO memberVO){
        List<ClsVO> voList = clsService.selectClass(memberVO);
        return voList;
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

    // 강사 정보 상세 조회 (완료)
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
