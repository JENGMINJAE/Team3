package com.green.Team3.member.controller;

import com.green.Team3.member.service.MemberServiceImpl;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    //로그인 페이지
    @GetMapping("/joinForm")
    public String joinForm(){
        return "content/member/join";
    }

    //회원가입
    @GetMapping("/join")
    public String join(MemberVO memberVO){

        //회원가입 쿼리
        memberService.join(memberVO);

        return "redirect:/member/join";
    }

}
