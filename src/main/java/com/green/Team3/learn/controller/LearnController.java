package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.service.LearnServiceImpl;
import com.green.Team3.learn.vo.AttendanceTypeVO;
import com.green.Team3.learn.vo.AttendanceVO;
import com.green.Team3.learn.vo.InsertAtdListVO;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    //출결 뿌려주기
    @GetMapping("/atd")
    public String selectAtd(Model model, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        model.addAttribute("atds",learnService.selectAtd());
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/attendance";
    }
    //출석부
    @ResponseBody
    @PostMapping("/changeStuOption")
    public Map<String, Object> changeModal(@RequestParam(name = "classNum")int classNum){
        Map<String,Object> map = new HashMap<>();
        List<AttendanceTypeVO> atdList = learnService.selectAtd();//출결 항목
        List<OperatorVO> studentList = consultService.selectClassNumAndStuNum(classNum);//반의 학생들 조회
        List<MemberVO> fullAttendanceList = learnService.fullAttendance(classNum);//학생 개인별 출석률? 조회
        boolean nowCheckAttendance = learnService.nowCheckAttendance(classNum);//오늘 출결체크를 했는지 확인
        map.put("atdList",atdList);
        map.put("studentList",studentList);
        map.put("fullAttendanceList",fullAttendanceList);
        map.put("nowCheckAttendance",nowCheckAttendance);
        return map;
    }

    //출석 추가
    @ResponseBody
    @PostMapping("/insertAttendance")
    public void insertAttendance(@RequestBody ArrayList<AttendanceVO> atdList){
        InsertAtdListVO vo = new InsertAtdListVO();
        vo.setAtdList(atdList);
        learnService.insertAttendance(vo);
    }
    @GetMapping("/classPercent")
    public String classPercent(){
        return "/content/teacher/class_percent";
    }
}
