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
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        //연락처 세팅 (010,0000, 0000 -> 010-0000-0000) 변환 후 set(저장)
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",", "-"));
        //이메일 세팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",", ""));
        //회원가입 쿼리
        memberService.join(memberVO);
        return "redirect:/";
    }

    //아이디 중복 확인
//    public int idCheck(String memberId){
//
//    }


    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
        System.out.println(loginInfo.getMemberRoll());
        //로그인 성공
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
            //memberRoll == 1 (학생)
            if (loginInfo.getMemberRoll() == 1){
                return "content/student/stu_list";
            }
            //memberRoll == 2 (강사)
            else if (loginInfo.getMemberRoll() == 2) {

                return "content/teacher/tea_list";
            }
            //memberRoll == 3 (관리자)
            else {
                return "content/admin/admin_list";
            }
        }
        System.out.println("일치하는 회원 정보가 없습니다.");
        session.setAttribute("loginInfo", null);
        System.out.println(memberVO);

        return "redirect:/";

//        if(loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//        return "content/member/login_result";

    }


}
