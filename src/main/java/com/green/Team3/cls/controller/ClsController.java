package com.green.Team3.cls.controller;

import com.green.Team3.cls.service.ClsServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClsController {
    @Resource(name = "clsService")
    private ClsServiceImpl clsService;

    @GetMapping("/testExam")
    public String testExam(){
        return "/content/teacher/test_exam";
    }
}
