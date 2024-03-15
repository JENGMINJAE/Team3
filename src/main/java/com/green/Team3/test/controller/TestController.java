package com.green.Team3.test.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//http://127.0.0.1:8081/test/testList
@RequestMapping("/test")
@Controller
public class TestController {


    @Resource(name = "testService")
  private TestServiceImpl testService;




    @GetMapping("/testList")
    public String testList(Model model){

        // ------------- 강사가 수업하는 수강명 목록 조회 ---------------
        List<ClsVO> classList = testService.selectTeacherClassList();
        System.out.println(classList);
        model.addAttribute("classList", classList);

        return "content/test/teacher_first_sc";

    }



    @RequestMapping("/scoreTeacher")
    public String scoreTeacher(Model model,
                               MemberVO memberVO, TestVO testVO,
                               @RequestParam(name="classNum") int classNum,
                               SearchTestVO searchTestvo) {


        // ----- teacher_test_List html에서 시험명 목록조회 ----

        //반 번호 넘겨서 평가명 추가 버튼 클릭 가능
        List<ClsVO> onlyClassNum = testService.onlyClassNum(classNum);
        model.addAttribute("onlyClassNum",onlyClassNum);

        //반 번호 넘겨서 시험지명 목록 조회
        List<TestVO> selectTestNames = testService.selectTest(testVO.getClassNum());
        model.addAttribute("selectTestNames", selectTestNames);


        //  반 학생명 목록 조회
        List<MemberVO> selectStuNames = testService.selectStuName(classNum);
        model.addAttribute("selectStuNames", selectStuNames);


        // 검색란
        List<TestVO> searchTests = testService.searchTestList(searchTestvo);
        model.addAttribute("searchTests",searchTests);
        System.out.println(searchTests);


        return "content/test/teacher_test_List";

    }

    //비동기 평가명 목록 조회
    @ResponseBody
    @PostMapping("/selectTest")
    public List<TestVO> selectTest(TestVO testVO) {

        List<TestVO> testNames = testService.selectTest(testVO.getClassNum());
        return testNames;

    }

    //비동기 평가명 저장
    @ResponseBody
    @PostMapping("/insertTest")
    public void insertTest(TestVO testVO) {
        testService.insertTest(testVO);

    }

    // 비동기 시험명 클릭시 학생별 성적 조회
    @ResponseBody
    @PostMapping("/selectScoreList")
    public List<TestScoreVO> selectScoreList(TestScoreVO testScoreVO, Model model) {
        // 시험명 클릭시 학생별 성적 조회
        List<TestScoreVO> scoreSelectList = testService.selectTestScore(testScoreVO.getTestNum());
        model.addAttribute("scoreSelectList", scoreSelectList);
        return scoreSelectList;
    }





    /// ------------------------- 점수 입력 새페이지 이동하기 -------------------
    @GetMapping("/goInputScore")
    public String goInputScore(@RequestParam(name = "testNum") int testNum, Model model) {

        // 넘버로 받는 반학생명 , 테스트명 조회
        List<MemberVO> memNumInfo = testService.memNumInfo(testNum);
        model.addAttribute("memNumInfo",memNumInfo);
        System.out.println(memNumInfo);


        // test 넘버를 비동기에 넘기기 위해서
        TestVO testNumInfo = testService.testNumInfo(testNum);
        model.addAttribute("testNumInfo",testNumInfo);

        // 넘버로 받는 랭킹이 포함된 점수 조회
        List<TestScoreVO> classScoresList = testService.selectTestScore(testNum);
        model.addAttribute("classScoresList", classScoresList);

        return "content/test/teacher_score";
    }


    // ---------------    성적입력하고  등록 버튼 클릭시 저장 --------------

    @PostMapping("/insertStuScore")
    public String insertStuScore(TestScoreVO testScoreVO) {

        testService.insertStuScore(testScoreVO);

        return "redirect:/test/goInputScore?testNum="+testScoreVO.getTestNum();


    }


    // --------- 점수 수정 버튼 클릭시 업데이트 -----


    @PostMapping("/updateScore")
    public String updateScore(@RequestParam(name = "score") List<Integer> scoreList
            , @RequestParam(name = "scoreNum") List<Integer> scoreNumList ,TestScoreVO testScoreVO){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@");

        for(int i = 0 ; i < scoreNumList.size() ; i++){
            TestScoreVO vo = new TestScoreVO();
            vo.setScore(scoreList.get(i));
            vo.setScoreNum(scoreNumList.get(i));
            testService.updateScore(vo);
        }

        return "redirect:/test/goInputScore?testNum="+ testScoreVO.getTestNum();
    }



    // 성적 통계 표 조회하는 페이지 이동하기

    @GetMapping("/totalScoreShow")
    public String totalScoreShow(TestScoreVO testScoreVO, TestVO testVO, @RequestParam(name="classNum") int classNum, Model model) {

        //   반 학생명 조회

        List<MemberVO> selectStuNames = testService.selectStuName(classNum);
        model.addAttribute("selectStuNames", selectStuNames);
        System.out.println(selectStuNames);

        // Test 명 조회
        List<TestVO> testNames = testService.totalTestSelect(testVO.getClassNum());
        model.addAttribute("testNames", testNames);
        System.out.println(testNames);

        //  점수 조회
        List<TestScoreVO> scoreSelectList = testService.totalStuScoreSelect(testVO.getClassNum());
        model.addAttribute("scoreSelectList", scoreSelectList);
        System.out.println(scoreSelectList);
        return "content/item/totalScore";
    }





}
