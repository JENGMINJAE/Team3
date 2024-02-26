package com.green.Team3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String firstPage() {
        return "content/member/firstPage";
    }
}