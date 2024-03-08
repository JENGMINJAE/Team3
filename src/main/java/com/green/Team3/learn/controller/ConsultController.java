package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consult")
public class ConsultController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;

    @GetMapping("/addConsultForm")
    public String addConsultForm(Model model){
        String memberId = "a";
        model.addAttribute("classInfo",homeworkService.selectClassByThisTeacher(memberId));
        return "/content/teacher/add_consult";
    }

    @PostMapping("/addConsult")
    public String addConsult(){

        return "";
    }
}
