package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(memberId));
        model.addAttribute("IngHomeworkList",homeworkService.selectIngHomework(memberId));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(memberId));
        return "/content/teacher/homework_list";
    }
    @PostMapping("/addHomeworkResult")
    private String addHomeworkResult(HomeworkVO homeworkVO,Model model){
        String memberId = "a";
        homeworkService.homeworkAdd(homeworkVO);
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(memberId));
        model.addAttribute("homeworkList",homeworkService.selectIngHomework(memberId));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(memberId));
        return "/content/teacher/homework_list";
    }

    @PostMapping("/updateHomework")
    private String updateHomework(HomeworkVO vo){
        homeworkService.updateHomework(vo);
        return "redirect:/homework/homeworkList";
    }

    @PostMapping("/deleteHomework")
    private String deleteHomework(HomeworkVO vo){
        homeworkService.deleteHomework(vo);
        return "redirect:/homework/homeworkList";
    }

    @ResponseBody
    @PostMapping("/modalChange")
    private Map<String,Object> modalChange(@RequestParam(name = "hwNum")int hwNum){
        HomeworkVO homeworkVO = new HomeworkVO();
        homeworkVO = homeworkService.selectOneHomework(hwNum);
        int classNum = homeworkVO.getClassNum();
        int teacherNum = homeworkService.selectTeacherNumByClassNum(classNum);
        List<HomeworkVO> classList = homeworkService.selectClassNumByTeacherNum(teacherNum);
        Map<String, Object> map = new HashMap<>();
        map.put("homeworkVO",homeworkVO);
        map.put("classList",classList);
        return map;
    }



}
