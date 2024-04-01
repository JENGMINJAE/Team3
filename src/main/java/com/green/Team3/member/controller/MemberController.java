package com.green.Team3.member.controller;

import com.green.Team3.member.service.MemberServiceImpl;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.util.MailService;
import com.green.Team3.util.MailVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;
    @Resource(name = "mailService")
    private MailService mailService;
    @Autowired
    private BCryptPasswordEncoder encoder;

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
        //회원가입 전 비밀번호 암호화
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));
        //회원가입 쿼리
        memberService.join(memberVO);
        return "redirect:/";
    }

    //아이디 중복 확인
//    public int idCheck(String memberId){
//
//    }


    //로그인
//    @PostMapping("/login")
//    public String login(MemberVO memberVO, HttpSession session, Model model){
//        MemberVO loginInfo = memberService.login(memberVO);
//        System.out.println(loginInfo.getMemberRoll());
//        //로그인 성공
//        if(loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//            //memberRoll == 1 (학생)
//            if (loginInfo.getMemberRoll() == 1){
//                model.addAttribute("memberRoll",loginInfo.getMemberRoll());
//                return "content/student/stu_list";
//            }
//            //memberRoll == 2 (강사)
//            else if (loginInfo.getMemberRoll() == 2) {
//                model.addAttribute("memberRoll",loginInfo.getMemberRoll());
//                return "content/teacher/tea_list";
//            }
//            //memberRoll == 3 (관리자)
//            else {
//                model.addAttribute("memberRoll",loginInfo.getMemberRoll());
//                return "content/admin/admin_list";
//            }
//        }
//        System.out.println("일치하는 회원 정보가 없습니다.");
//        session.setAttribute("loginInfo", null);
//        System.out.println(memberVO);
//
//        return "redirect:/";

//        if(loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//        return "content/member/login_result";

//    }
    @GetMapping("/stuLogin")
    public String stuLogin(){
        return "/content/student/stu_list";
    }
    @GetMapping("/teaLogin")
    public String teaLogin(){
        return "/content/teacher/tea_list";
    }

    @GetMapping("/logoClick")
    public String logoClick(){
        return "redirect:/";
    }

    @GetMapping("/findPasswordForm")
    public String findPw(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg,Model model){
        model.addAttribute("errorMsg",errorMsg);
//        mailService.sendHTMLEmail();
        return "/content/member/findPassword";
    }


    @ResponseBody
    @PostMapping("/findPwFetch")
    public boolean findPwFetch(MemberVO memberVO){
        String memberEmail = memberService.getMemberEmail(memberVO);
        if(memberEmail != null){
            //비밀번호 변경
            //임시 비밀번호 생성
            String imsiPw = mailService.createRandomPw();
            //암호화
            String encodedPw = encoder.encode(imsiPw);
            memberVO.setMemberPw(encodedPw);
            memberService.updateMemberPw(memberVO);
            //메일 보내기
            MailVO mailVO = new MailVO();
            mailVO.setTitle("임시 비밀번호 발송");
            mailVO.setRecipient(memberEmail);
            mailVO.setContent("임시 비밀번호 : "+imsiPw);
            mailService.sendSimpleEmail(mailVO);

        }
        return memberEmail != null ? true : false;
    }

}
