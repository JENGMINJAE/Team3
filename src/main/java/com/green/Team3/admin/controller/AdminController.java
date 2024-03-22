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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    // 강사 정보 상세 조회
    @ResponseBody
    @PostMapping("/selectTeacher")
    public List<ClsVO> detailTeacher(@RequestBody ClsVO clsVO){
        List<ClsVO> list = adminService.detailTeacher(clsVO.getTeacherNum());
        return list;
    }

    // 회원 관리 페이지 이동
    @RequestMapping("/goMemberList")
    public String memberList(Model model, SearchVO searchVO,
                             @RequestParam(name = "memberId", required = false, defaultValue = "")String updateMemberId){
        // 전체 회원 목록 조회
        List<MemberVO> list = memberService.selectMembers(searchVO);
        model.addAttribute("memberList", list);
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

    // 인적 사항 변경 쿼리 실행(변경 버튼 클릭 시) -> 회원 관리에서
    @PostMapping("/changePersonalInfo")
    public String changePersonalInfo(MemberVO memberVO){
        adminService.changePersonalInfo(memberVO);
        return "redirect:/admin/goMemberList?memberId=" + memberVO.getMemberId();
    }

    @PostMapping("/editPersonInfo")
    public String editPersonInfo(MemberVO memberVO, Model model, @RequestParam(name = "classNum")int classNum){
        adminService.changePersonalInfo(memberVO);
        model.addAttribute("updateMemberId", memberVO.getMemberId());
        return "redirect:/admin/goClassInfo?classNum=" + classNum;
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

    // 학급 생성 페이지 이동
    @GetMapping("/makeClassForm")
    public String makeClassForm(Model model){
        model.addAttribute("clsList", clsService.selectAllClass());
        model.addAttribute("teachers", adminService.selectTeacherName());
        return "content/admin/make_class_form";
    }

    // 학급 생성 버튼 클릭 시 실행 메소드
    @PostMapping("/makeClass")
    public String makeClass(ClsVO clsVO){
        adminService.makeCls(clsVO);
        return "redirect:/admin/makeClassForm";
    }

    // 근무 상태 변경
    @PostMapping("/changeAttendance")
    public String changeAttendance(TeacherVO teacherVO){
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

    // 인적 사항 페이지 이동
    @ResponseBody
    @PostMapping("goStuInfo")
    public MemberVO goStuInfo(@RequestBody MemberVO memberVO){
        return memberService.memberDetail(memberVO);
    }

    //    ----------------------- 완료 ---------------------------

    // 반에 학생을 추가하기 위한 학생 목록 조회 페이지 이동
    @ResponseBody
    @PostMapping("/goInsertStu")
    public List<MemberVO> goInsertStu(){
        return memberService.selectStudents();
    }

    // 결제 시스템 페이지 이동 (진행 중)
    @ResponseBody
    @PostMapping("/goPayment")
    public List<ClsVO> goPayment(@RequestBody MemberVO memberVO){

        System.out.println(memberVO.getMemberId());
//        adminService.requestPayInfo(memberId);
        return adminService.requestPayInfo(memberVO.getMemberId());
    }

    // 결제 성공 시 이동할 페이지
    @GetMapping("/successPayment")
    public String successPayment(){
        return "content/admin/payment_system";
    }
}
