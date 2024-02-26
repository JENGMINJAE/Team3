package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminService adminService;

    // 강사 권한 수정 (학생 -> 강사)
    @GetMapping("/updateTeacher")
    public String updateTeacher(MemberVO memberVO){
        adminService.updateTeacher(memberVO);
        return "";
    }
}
