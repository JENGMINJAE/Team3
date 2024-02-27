package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminService adminService;

    // 관리자 클릭 시 페이지 이동
    @GetMapping("/notice")
    public String notice(){
        return "content/admin/admin_list";
    }

    // 강사 권한 수정 페이지 이동
    @GetMapping("/goAdminTeacher")
    public String goAdminTeacher(){
        return "content/admin/admin_teacher";
    }
    // 강사 권한 수정 (학생 -> 강사)
    @GetMapping("/updateTeacher")
    public String updateTeacher(MemberVO memberVO){
        adminService.updateTeacher(memberVO);
        return "redirect:/admin/goAdminTeacher";
    }
}
