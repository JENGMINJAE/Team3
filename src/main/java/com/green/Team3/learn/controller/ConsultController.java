package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
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

    @GetMapping("/addConsultContentForm")
    public String addConsult(@RequestParam(name = "consultNum")int consultNum,Model model){
        model.addAttribute("consultVO",consultService.selectOneConsult(consultNum));
        return "/content/teacher/add_consult_content";
    }

    @PostMapping("/addConsultContent")
    public String addConsultContent(ConsultVO consultVO,Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        consultService.addConsultContent(consultVO);
        model.addAttribute("consultList",consultService.contentComplete(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/content_complete";
    }
    @GetMapping("/contentComplete")
    public String contentComplete(Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("consultList",consultService.contentComplete(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/content_complete";
    }

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
    private String updateConsult(@RequestParam(name = "beforeTitle")String beforeTitle,ConsultVO consultVO){
        consultService.updateConsult(consultVO);
        calenderService.deleteCalender(beforeTitle);
        EventCalenderVO eVO = new EventCalenderVO();
        EventTypeVO eventTypeVO = calenderService.selectEventTypeForTeacherByConsult();
        eVO.setStart(consultVO.getConsultDate());
        eVO.setMemberId(consultVO.getMemberId());
        String tt = eventTypeVO.getEventTypeName() + "-" + calenderService.selectClassNameByClassNum(consultVO.getClassNum()) + "-" + consultVO.getMemberId() + "-" + consultVO.getConsultDate();
        eVO.setTitle(tt);
        eVO.setEventTypeNum(eventTypeVO.getEventTypeNum());
        calenderService.insertEventCalender(eVO);
        return "redirect:/consult/consultList";
    }

    @PostMapping("/deleteConsult")
    private String deleteConsult(@RequestParam(name = "consultNum")int consultNum,
                                 @RequestParam(name = "title")String title){
        consultService.deleteConsult(consultNum);
        calenderService.deleteCalender(title);
        return "redirect:/consult/consultList";
    }
}
