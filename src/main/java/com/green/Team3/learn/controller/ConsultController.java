package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.ConsultServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consult")
public class ConsultController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;

    @GetMapping("/addConsultForm")
    public String addConsult(){
        return "/content/teacher/add_consult";
    }
}
