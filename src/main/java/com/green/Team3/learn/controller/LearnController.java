package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.LearnServiceImpl;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learn")
public class LearnController {
    @Resource(name = "learnService")
    private LearnServiceImpl learnService;

    @GetMapping("/atd")
    public String selectAtd(Model model){
        model.addAttribute("atds",learnService.selectAtd());
        return "/content/teacher/attendance";
    }
}
