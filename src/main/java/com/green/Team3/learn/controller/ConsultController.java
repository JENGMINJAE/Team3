package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.learn.vo.EventTypeVO;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consult")
public class ConsultController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;

    @GetMapping("/addConsultForm")
    public String addConsultForm(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classInfo",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/add_consult";
    }

    @ResponseBody
    @PostMapping("/changeStuOption")
    public List<OperatorVO> changeStuOption(@RequestParam(name = "classNum")int classNum){
        List<OperatorVO> list = consultService.selectClassNumAndStuNum(classNum);
        return list;
    }

//    @PostMapping("/addConsult")
//    public String addConsult(ConsultVO consultVO){
//        consultService.insertConsult(consultVO);
//        return "/content/teacher/consult_list";
//    }
    @GetMapping("/consultAddCalender")
    public String consultAddCalender(Authentication authentication,Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classInfo",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/consult_add_calender";
    }

    @GetMapping("/consultList")
    public String consultList(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("endConsultList",consultService.selectEndConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        model.addAttribute("willConsultList",consultService.selectWillConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        model.addAttribute("todayConsultList",consultService.selectTodayConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/consult_list";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @ResponseBody
    @PostMapping("/modalChange")
    private Map<String,Object> modalChange(@RequestParam(name = "consultNum")int consultNum){
        ConsultVO consultVO = new ConsultVO();
        consultVO = consultService.selectOneConsult(consultNum);
        int classNum = consultVO.getClassNum();
        int teacherNum = homeworkService.selectTeacherNumByClassNum(classNum);
        List<ConsultVO> classList = consultService.selectClassNumByTeacherNumConsult(teacherNum);
        Map<String, Object> map = new HashMap<>();
        map.put("consultVO",consultVO);
        map.put("classList",classList);
        return map;
    }

    @PostMapping("/updateConsult")
    private String updateConsult(ConsultVO consultVO){
        consultService.updateConsult(consultVO);
//        memberId,consultDate,classNum
        return "redirect:/consult/consultList";
    }










}
