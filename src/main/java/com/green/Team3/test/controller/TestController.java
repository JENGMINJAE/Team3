package com.green.Team3.test.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                               @RequestParam(name ="classNum") int classNum,
                               SearchTestVO searchTestVO) {


        // ----- teacher_test_List html에서 시험명 목록조회 ----

        //반 번호 넘겨서 평가명 추가 버튼 클릭 가능
        ClsVO onlyClassNum =testService.onlyClassNum(classNum);
        model.addAttribute("onlyClassNum", onlyClassNum);

        //반 번호 넘겨서 시험지명 목록 조회
        List<TestVO> selectTestNames = testService.selectTest(classNum);
        model.addAttribute("selectTestNames", selectTestNames);


        //  반 학생명 목록 조회
        List<MemberVO> selectStuNames = testService.selectStuName(classNum);
        model.addAttribute("selectStuNames", selectStuNames);


        // 검색란
        List<TestVO> searchTests = testService.searchTestList(searchTestVO);
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
        return "content/test/totalScore";
    }


//    // 점수 직접 입력하기
//    @GetMapping("/directScore")
//    public String directScore(@RequestParam(name = "testNum") int testNum, Model model) {
////        List<TestVO> testNumInfo = testService.testNumInfo(testNum);
////        model.addAttribute("testNumInfo",testNumInfo);
//        return "content/test/teacher_sub_sc";
//    }

// ///////////////////////////////////////////////////


    // 과목없음 과목있음 페이지 다르게 이동
    @GetMapping("/goTestN")
    public String goTestN(@RequestParam(name = "testNum") int testNum, @RequestParam(name = "classNum")int classNum
            , Model model) {

        List<TestSubjectVO> subsList = testService.subSelect(testNum);
        // System.out.println("#######################" + subsList);
        model.addAttribute("subsList", subsList);

        List<MemberVO> stuCnt = testService.stuCnt(classNum);
        model.addAttribute("stuCnt", stuCnt);

        for (TestSubjectVO subs : subsList) {
            System.out.println("####################" + subs);
            if (subs.getSubTestNum() != 0) {
                return "content/test/teacher_sub_sc";
            }
        }

        return "redirect:/test/goInputScore?testNum=" + testNum;
    }


// 과목별 시험입력페이지 가서 버튼 클릭시 학생명, 과목명 자동조회
    @ResponseBody
    @RequestMapping ("/subSelectStu")
    public Map<String, Object> subSelectStu(@RequestParam(name ="testNum") int testNum,
                                            @RequestParam(name = "classNum")int classNum ){

        List<TestSubjectVO> subsList= testService.subSelect(testNum);

        List<MemberVO> stuCnt = testService.stuCnt(classNum);

        Map<String, Object> map = new HashMap<>();
        map.put("subsList",subsList);
        map.put("stuCnt", stuCnt);

        System.out.println("@@@@@@@@@@@@@@@" +map);
        return map;

    }

// 모달 과목있을시 메인테스트명, 일자 저장
    @ResponseBody
    @PostMapping("/insertSubSc")
    public void insertSubSc(TestVO testVO) {
        testService.subMainTestInsert(testVO);

    }

    // 과목저장
    @ResponseBody
    @PostMapping("/goInsertSub")
    public void goInsertSub(TestSubjectVO testSubjectVO){

        testService.insertSub(testSubjectVO);

    }

    //비동기 과목명 목록 조회
    @ResponseBody
    @PostMapping("/selectSubTest")
    public Map<String, Object> selectSubTest(@RequestParam(name = "testNum")int testNum) {

        List<TestSubjectVO> subNames = testService.selectSubList(testNum);
        System.out.println(subNames);

        List<TestVO> testNum2 = testService.testNumInfo2(testNum);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("subNames",subNames);
        map2.put("testNum2", testNum2);


        return map2;

    }

// 입력한 과목 점수 저장

    @PostMapping("/insertSubNtest")
    public String insertSubNtest(@RequestParam(name="testNum") List<Integer> testNums,
                                 @RequestParam(name="score") List<Integer> scores,
                                 @RequestParam(name ="memberId") List<String> memberIds,
                                 @RequestParam(name = "subTestNum")List<Integer> subTestNums,
                                 TestScoreVO testScoreVO){

        for(int j =0; j<scores.size(); j++){

            TestScoreVO ts = new TestScoreVO();

            ts.setTestNum(testNums.get(j));
            ts.setScore(scores.get(j));
            ts.setMemberId(memberIds.get(j));
            ts.setSubTestNum(subTestNums.get(j));
            testService.insertSubScore(ts);

        }

        return "redirect:/test/goTestN?testNum="+ testScoreVO.getTestNum()+"&classNum="+ testScoreVO.getTestOneVo().getClassNum();

    }




}
