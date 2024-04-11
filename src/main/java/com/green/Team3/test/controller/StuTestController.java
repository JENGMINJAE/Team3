package com.green.Team3.test.controller;


import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import com.green.Team3.test.service.StuTestServiceImpl;
import com.green.Team3.test.service.TestService;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.TestAskVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stuTest")
@Controller
public class StuTestController {

    @Resource(name = "stuTestService")
    private StuTestServiceImpl stuTestService;

    @Resource(name = "testService")
    private TestServiceImpl testService;


    // ??????????????? 진행중
    // /////////////////////////학생이 로그인 했을때 성적확인하기 ///////////////////////////////////////

    @GetMapping("/stuTestFirst")
    public String stuTestFirst(Model model, Authentication authentication){

        User user=(User) authentication.getPrincipal();

        MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
        model.addAttribute("stuInfoService",stuInfoService);

        List<OperatorVO> clCnt = stuTestService.stuClCnt(user.getUsername());
        model.addAttribute("clCnt",clCnt);


        return "content/student/student_test_search";
    }

    // 학생이 수강별 조회
    @ResponseBody
    @PostMapping("/classListSearch")
    public List<TestVO> classListSearch(Authentication authentication){

        User user=(User) authentication.getPrincipal();

        List<TestVO> stuCLTest =stuTestService.selectStuCLTest(user.getUsername());
        System.out.println("$$$$$$$$$$$$$$$"+ stuCLTest);
        return stuCLTest;
    }

    // 학생이 기간별 조회
    @ResponseBody
    @PostMapping("/testListSearch")
    public List<TestVO> testListSearch(Authentication authentication) {

        User user=(User) authentication.getPrincipal();

        List<TestVO> stuTest = stuTestService.selectStuTestDetail(user.getUsername());
        return stuTest;
    }

    // 학생이 과목별 조회
    @ResponseBody
    @PostMapping("/subListSearch")
    public List<TestSubjectVO> subListSearch(Authentication authentication){

        User user=(User) authentication.getPrincipal();

        List<TestSubjectVO> stuSubTest =stuTestService.selectStuSub(user.getUsername());
        System.out.println("8888888888888888888888"+stuSubTest);
        return stuSubTest;
    }

    // 학생이 전체이수표 조회
    @ResponseBody
    @PostMapping("/totalListSearch")
    public List<ClsVO> totalListSearch(Authentication authentication){

        User user=(User) authentication.getPrincipal();
        List<ClsVO> totalStuTest =stuTestService.totalSelectTest(user.getUsername());
        return totalStuTest;
    }



    // ///////////////////////////////////////////////////

    // 학생이 본인 단일 시험 성적 상세성적 조회
    @GetMapping("/goMyScore")
    public String goMyScore(TestScoreVO testScoreVO, Authentication authentication, Model model){

        User user=(User) authentication.getPrincipal();

        MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
        model.addAttribute("stuInfoService",stuInfoService);
        System.out.println(stuInfoService);


        TestScoreVO mainMyScore= stuTestService.mainTestMyScore(testScoreVO);
        model.addAttribute("mainMyScore",mainMyScore);
        System.out.println(mainMyScore);

        return "content/student/student_test_check";

    }

    // 학생이 본인 과목시험 성적 상세성적 조회
    @GetMapping("/goMysubScore")
    public String goMysubScore(TestScoreVO testScoreVO, Authentication authentication,
                               @RequestParam(name = "testNum") int testNum, Model model){
        User user=(User) authentication.getPrincipal();

        MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
        model.addAttribute("stuInfoService",stuInfoService);


        List<TestScoreVO> subMyScores= stuTestService.subTestMyScore(testScoreVO);
        model.addAttribute("subMyScores",subMyScores);


        return "content/student/student_subject_check";
    }




    // 학생이 본인 성적 모든성적 조회 프린트

    @GetMapping("totalStuPrint")
    public String totalStuPrint(Authentication authentication){
        User user=(User) authentication.getPrincipal();

        return "content/student/student_total_test";
    }



// ############################# (학생) 이의신청 페이지  #############################


    // (학생)이의신청 목록페이지 (첫페이지)
    @GetMapping("/stuAskFirst")
    public String stuAskFirst(Model model, Authentication authentication){
        User user=(User) authentication.getPrincipal();
        List<TestAskVO> testAskList = stuTestService.selectStuAsk(user.getUsername());
        model.addAttribute("testAskList",testAskList);

         return "content/student/student_test_ask";
    }

    // (학생) 이의신청 글 적기 페이지
    @GetMapping("/stuAskWrite")
    public String stuAskWrite(Model model, Authentication authentication ){
            User user=(User) authentication.getPrincipal();

            MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
            model.addAttribute("stuInfoService",stuInfoService);

            List<TestVO> stuTest = stuTestService.selectStuTestDetail(user.getUsername());
            model.addAttribute("stuTest",stuTest);

            List<ClsVO> totalStuTest =stuTestService.totalSelectTest(user.getUsername());
            model.addAttribute("totalStuTest", totalStuTest);

        return "content/student/student_ask_write";
    }


    // (학생) 이의신청 글 저장
    @PostMapping("/insertStuAsk")
    public String insertStuAsk(TestAskVO testAskVO, Authentication authentication){
        User user=(User) authentication.getPrincipal();

        stuTestService.insertStuAsk(testAskVO);

        return "redirect:/stuTest/stuAskFirst?memberId=" + testAskVO.getMemberId();
    }

    @GetMapping("/stuAskDetail")
    public String stuAskDetail(TestAskVO testAskVO, Model model,Authentication authentication){

        User user=(User) authentication.getPrincipal();

        TestAskVO testAskOne = stuTestService.stuAskDetail(testAskVO);
        model.addAttribute("testAskOne",testAskOne);
        System.out.println(testAskOne);

       TeacherVO teacherID = stuTestService.askMemberId(user.getUsername());
        System.out.println("999999"+teacherID);

        if(teacherID!=null){
            List<TestAskVO> thTestAskList = testService.selTeacherAsk(user.getUsername());
            model.addAttribute("thTestAskList", thTestAskList);
            System.out.println("0543678889"+thTestAskList);
        }
        return "content/student/student_ask_detail";
    }


    //상세내용을 가지고 글 수정 페이지로 가기
    @GetMapping("/goUpdatePG")
    public String goUpdatePG(TestAskVO testAskVO ,Model model, Authentication authentication){


        TestAskVO testAskOne = stuTestService.stuAskDetail(testAskVO);
        model.addAttribute("testAskOne",testAskOne);
        return "content/student/student_ask_wUpdate";
    }

//    //글 1개 수정하기
//    @PostMapping("askUpdate")
//    public String askUpdate(BoardVo boardVo){
//        boardService.updateBoard(boardVo);
//        return "redirect:/board/list";
//    }






}
