package com.green.Team3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String firstPage(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg, Model model) {
        model.addAttribute("errorMsg",errorMsg);
        return "content/member/firstPage";
    }
}