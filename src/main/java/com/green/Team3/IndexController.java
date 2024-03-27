package com.green.Team3;

import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;
    @GetMapping("/")
    public String firstPage(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg, Model model) {
        model.addAttribute("errorMsg",errorMsg);
        consultService.autoDeleteConsult();
        calenderService.autoDeleteCalender();
        return "content/member/firstPage";
    }
}