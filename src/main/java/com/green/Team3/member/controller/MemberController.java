package com.green.Team3.member.controller;

import com.green.Team3.member.service.MemberServiceImpl;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    //회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm(){
        return "content/member/join";
    }

    //회원가입
    @GetMapping("/join")
    public String join(MemberVO memberVO){

        //연락처 세팅 (010,0000, 0000 -> 010-0000-0000) 변환 후 set(저장)
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",", "-"));

        //이메일 세팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",", ""));

        //회원가입 쿼리
        memberService.join(memberVO);

        return "redirect:/";
    }

    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
        //로그인 성공
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }
        return "content/member/login_result";
    }


}
