package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calender")
public class CalenderController {
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;

    @ResponseBody
    @PostMapping("/changeStuOption")
    public List<OperatorVO> changeStuOption(@RequestParam(name = "classNum")int classNum){
        List<OperatorVO> list = consultService.selectClassNumAndStuNum(classNum);
        return list;
    }

    @PostMapping("/addCalender")
    public String addCalender(@RequestParam(name = "memberId")String memberId,
                              @RequestParam(name = "classNum")int classNum,
                              @RequestParam(name = "start")String start){
        EventTypeVO eventTypeVO = calenderService.selectEventTypeForTeacherByConsult();
        EventCalenderVO calenderVO = new EventCalenderVO();
        String title = memberId;
        title = eventTypeVO.getEventTypeName() + "-" + calenderService.selectClassNameByClassNum(classNum) + "-" + title;
        calenderVO.setEventTypeNum(eventTypeVO.getEventTypeNum());
        calenderVO.setStart(start);
        calenderVO.setTitle(title);
        System.out.println(calenderVO);
        calenderService.insertEventCalender(calenderVO);
        return "redirect:/consult/consultList";
    }

    @ResponseBody
    @PostMapping("/addEventBar")
    public List<EventCalenderVO> addEventBar(){
        return calenderService.addEventBar();
    }
}
