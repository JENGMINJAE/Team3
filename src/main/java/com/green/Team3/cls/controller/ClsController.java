package com.green.Team3.cls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClsController {
    @GetMapping("/testExam")
    public String testExam(){
        return "/content/teacher/test_exam";
    }
}
