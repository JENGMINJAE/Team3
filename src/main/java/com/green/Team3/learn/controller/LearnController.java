package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.service.LearnServiceImpl;
import com.green.Team3.learn.vo.AttendanceTypeVO;
import com.green.Team3.learn.vo.AttendanceVO;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/learn")
public class LearnController {
    @Resource(name = "learnService")
    private LearnServiceImpl learnService;
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;

    @GetMapping("/atd")
    public String selectAtd(Model model, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        model.addAttribute("atds",learnService.selectAtd());
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/attendance";
    }

    @ResponseBody
    @PostMapping("/changeStuOption")
    public Map<String, Object> changeModal(@RequestParam(name = "classNum")int classNum){
        Map<String,Object> map = new HashMap<>();
        List<AttendanceTypeVO> atdList = learnService.selectAtd();
        List<OperatorVO> studentList = consultService.selectClassNumAndStuNum(classNum);
        map.put("atdList",atdList);
        map.put("studentList",studentList);
        return map;
    }
}
