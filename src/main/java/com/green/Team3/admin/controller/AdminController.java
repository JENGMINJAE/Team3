package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.service.ClsService;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.learn.service.ConsultService;
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

    @Resource(name = "consultService")
    private ConsultService consultService;

    // 관리자 클릭 시 페이지 이동
    @GetMapping("/notice")
    public String notice(){
        return "content/admin/admin_list";
    }

    // 강사 관리 페이지 이동
    @GetMapping("/goAdminTeacher")
    public String goAdminTeacher(Model model, @RequestParam(name = "teacherNum", required = false, defaultValue = "0")int teacherNum){
        List<TeacherVO> list = adminService.selectTeachers();
        System.out.println(list);
        model.addAttribute("teacherList", list); // 강사 목록 조회
        model.addAttribute("updateTeacherNum", teacherNum);
        return "content/admin/admin_teacher";
    }

    // 강사 정보 상세 조회 (작업 중)
    @ResponseBody
    @PostMapping("/selectTeacher")
    public List<ClsVO> detailTeacher(@RequestBody ClsVO clsVO){
//        System.out.println(clsVO);
        List<ClsVO> list = adminService.detailTeacher(clsVO.getTeacherNum());
//        System.out.println(list);
        return list;
    }

    // 회원 관리 페이지 이동
    @RequestMapping("/goMemberList")
    public String memberList(Model model, SearchVO searchVO,
                             @RequestParam(name = "memberId", required = false, defaultValue = "")String updateMemberId){
        // 전체 회원 목록 조회
        model.addAttribute("memberList", memberService.selectMembers());
        // MEMBER_ROLL 목록 조회 (관리, 강사, 회원)
        model.addAttribute("rollList", adminService.rollList());
        model.addAttribute("updateMemberId", updateMemberId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(searchVO.getSearchType());
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
    // 해당 회원의 수강 목록 페이지 이동 (모달)
    @ResponseBody
    @PostMapping("/showClass")
    public List<ClsVO> showClass(@RequestBody MemberVO memberVO){
        List<ClsVO> voList = clsService.selectClass(memberVO);
        return voList;
    }

    // 학급 생성 페이지 이동 (완료)
    @GetMapping("/makeClassForm")
    public String makeClassForm(Model model){
        model.addAttribute("clsList", clsService.selectAllClass());
        model.addAttribute("teachers", adminService.selectTeacherName());
        return "content/admin/make_class_form";
    }

    // 학급 생성 버튼 클릭 시 실행 메소드 (완료)
    @PostMapping("/makeClass")
    public String makeClass(ClsVO clsVO){
        adminService.makeCls(clsVO);
        return "redirect:/admin/makeClassForm";
    }

    // 근무 상태 변경 (완료)
    @PostMapping("/changeAttendance")
    public String changeAttendance(TeacherVO teacherVO){
        System.out.println(teacherVO);
        adminService.changeAttendance(teacherVO);
        return "redirect:/admin/goAdminTeacher";
    }

    // 선택한 반의 상세 정보 조회 페이지 이동
    @GetMapping("/goClassInfo")
    public String changeClass(@RequestParam(name = "classNum")int classNum, Model model){
        model.addAttribute("clsInfo", clsService.selectClassDetail(classNum));
        model.addAttribute("teachers", adminService.selectTeacherName());
        model.addAttribute("students", consultService.selectClassNumAndStuNum(classNum));
        return "content/admin/change_class";
    }

    // 반정보 수정 쿼리 실행
    @PostMapping("/updateClass")
    public String updateClass(ClsVO clsVO){
       adminService.updateClass(clsVO);
       return "redirect:/admin/goClassInfo?classNum=" + clsVO.getClassNum();
    }

    //    ----------------------- 완료 ---------------------------

    // 인적 사항 페이지 이동
    @GetMapping("goStuInfo")
    public String goStuInfo(MemberVO memberVO, Model model){
        model.addAttribute("detail", memberService.memberDetail(memberVO));
        System.out.println(memberService.memberDetail(memberVO));
        return "content/admin/show_stu_info";
    }

}
