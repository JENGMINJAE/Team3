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
        String memberId = "a";
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(memberId));
        return "/content/teacher/add_homework";
    }
    @GetMapping("/homeworkList")
    private String homeworkList(Model model){
        String memberId = "a";
        model.addAttribute("IngHomeworkList",homeworkService.selectIngHomework(memberId));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(memberId));
        return "/content/teacher/homework_list";
    }
    @PostMapping("/addHomeworkResult")
    private String addHomeworkResult(HomeworkVO homeworkVO,Model model){
        String memberId = "a";
        homeworkService.homeworkAdd(homeworkVO);
        model.addAttribute("homeworkList",homeworkService.selectIngHomework(memberId));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(memberId));
        return "/content/teacher/homework_list";
    }

    @PostMapping("/updateHomework")
    private String updateHomework(){
        return "redirect:/homework/homeworkList";
    }

    @PostMapping("/deleteHomework")
    private String deleteHomework(HomeworkVO vo){
        homeworkService.deleteHomework(vo);
        return "redirect:/homework/homeworkList";
    }





}
