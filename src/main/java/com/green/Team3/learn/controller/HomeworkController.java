package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;

    @GetMapping("/addHomework")
    private String addHomework(Model model){
        String memberId = "t";
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(memberId));
        return "/content/teacher/add_homework";
    }
    @PostMapping("/addHomeworkResult")
    private String addHomeworkResult(HomeworkVO homeworkVO){
        homeworkService.homeworkAdd(homeworkVO);
        return "/";
    }
}
