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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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



    //@RequestParam(name="teacherNum")int teacherNum
    @GetMapping("/testList")
    public String testList(Model model, Authentication authentication){
        User user=(User) authentication.getPrincipal();
        // ------------- 강사가 수업하는 수강명 목록 조회 ---------------
        // 로그인한 유저 아이디 user.getUsername()
        List<ClsVO> classList = testService.selectTeacherClassList(user.getUsername());
        System.out.println("@@@@@@@@@@@@@@@@@@@!!!!!!!!!!!!!!!!!"+classList);
        model.addAttribute("classList", classList);

        // ----- 수업듣는 학생인원 조회-----
//        List<ClsVO> classStuList = testService.selectClassStuCnt(user.getUsername());
//        System.out.println(classStuList);
//        model.addAttribute("classStuList",classStuList);

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
    public Map<String, Object> selectScoreList(TestScoreVO testScoreVO, Model model, TestVO testVO) {
        // 시험명 클릭시 학생별 성적 조회
        //List<TestScoreVO> scoreSelectList = testService.selectTestScore(testScoreVO.getTestNum());
        //model.addAttribute("scoreSelectList", scoreSelectList);

        List<TestVO> testSelectList = testService.testNumInfo2(testVO.getTestNum());
        model.addAttribute("testSelectList", testSelectList);

        List<TestSubjectVO> subDetailList = testService.selectSubList(testScoreVO.getTestNum());

        Map<String, Object> map3 = new HashMap<>();
        map3.put("testSelectList",testSelectList);
        map3.put("subDetailList", subDetailList);

        System.out.println("@@@@@@@@@@@@@@@" +map3);


        return map3;
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

//    @GetMapping("/totalScoreShow")
//    public String totalScoreShow(TestScoreVO testScoreVO, TestVO testVO, @RequestParam(name="classNum") int classNum, Model model) {
//
//        //   반 학생명 조회
//
//        List<MemberVO> selectStuNames = testService.selectStuName(classNum);
//        model.addAttribute("selectStuNames", selectStuNames);
//        System.out.println(selectStuNames);
//
//        // Test 명 조회
//        List<TestVO> testNames = testService.totalTestSelect(testVO.getClassNum());
//        model.addAttribute("testNames", testNames);
//        System.out.println(testNames);
//
//        //  점수 조회
//        List<TestScoreVO> scoreSelectList = testService.totalStuScoreSelect(testVO.getClassNum());
//        model.addAttribute("scoreSelectList", scoreSelectList);
//        System.out.println(scoreSelectList);
//        return "content/test/totalScore";
//    }


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


    // ///////////////////////////   시험정보 업데이트  하기//////////////////////////////////////////////

    // 메인테스트 1개 상세정보 조회

    @ResponseBody
    @PostMapping("/selUpdateMainTest")
    public TestVO selUpdateMainTest(@RequestParam(name = "testNum") int testNum){

        TestVO mainDetailOne= testService.testNumInfo(testNum);
        System.out.println(mainDetailOne);

        return mainDetailOne;
    }


    // 메인테스트 full 상세정보 수정
    @ResponseBody
    @PostMapping("/updateMainTest")
    public void updateMainTest(TestVO testVO){
        testService.updateTestDetail(testVO);

    }


    // 메인테스트 만점제외하고 상세정보 수정
    @ResponseBody
    @PostMapping("/updateMainTwo")
    public void updateMainTwo(TestVO testVO){
        testService.updateTestDeTwo(testVO);

    }


    // 과목상세정보 1개 조회
    @ResponseBody
    @PostMapping("/selUpdateSubTest")
    public TestSubjectVO selUpdateSubTest(@RequestParam(name = "subTestNum")int subTestNum){

      TestSubjectVO subDetailOne= testService.selectSubOne(subTestNum);
        System.out.println("22222"+subDetailOne);

     return subDetailOne;
    }

    // 과목 상세정보 수정
    @ResponseBody
    @PostMapping("/updateSubTest")
    public void updateSubTest(TestSubjectVO testSubjectVO){
        testService.updateSubDetail(testSubjectVO);

    }





    //
    // /////////////////////////학생이 로그인 했을때 성적확인하기 ///////////////////////////////////////

    @GetMapping("/stuTestFirst")
    public String stuTestFirst(Model model){

        MemberVO stuInfoService = testService.selectStuTest();
        model.addAttribute("stuInfoService",stuInfoService);
        System.out.println(stuInfoService);


//        List<OperatorVO> stuCLTest =scoreService.selectStuCLTest();
//        model.addAttribute("stuCLTest",stuCLTest);
//        System.out.println(stuCLTest);
        return "content/member/student_test_search";
    }

    // 학생이 수강별 조회
    @ResponseBody
    @PostMapping("/classListSearch")
    public List<TestVO> classListSearch(@RequestParam(name = "memberId")String memberId){

        List<TestVO> stuCLTest =testService.selectStuCLTest(memberId);
        return stuCLTest;
    }

    // 학생이 시험별 조회
    @ResponseBody
    @PostMapping("/testListSearch")
    public List<TestVO> testListSearch(@RequestParam(name = "memberId")String memberId) {

        List<TestVO> stuTest = testService.selectStuTestDetail(memberId);
        return stuTest;
    }

    // 학생이 과목별 조회
    @ResponseBody
    @PostMapping("/subListSearch")
    public List<TestSubjectVO> subListSearch(@RequestParam(name = "memberId")String memberId){

        List<TestSubjectVO> stuSubTest =testService.selectStuSub(memberId);
        return stuSubTest;
    }

    // 학생이 전체이수표 조회
    @ResponseBody
    @PostMapping("/totalListSearch")
    public List<OperatorVO> totalListSearch(@RequestParam(name = "memberId")String memberId){

        List<OperatorVO> totalStuTest =testService.totalSelectTest(memberId);
        return totalStuTest;
    }



    // ///////////////////////////////////////////////////

    // 학생이 본인 성적 상세성적 조회
    @GetMapping("/goMyScore")
    public String goMyScore(TestScoreVO testScoreVO, @RequestParam(name = "memberId") String memberId,
                            @RequestParam(name = "testNum") int testNum
            , Model model){


        List<TestScoreVO> mainMyScore= testService.mainTestMyScore(testScoreVO);
        model.addAttribute("mainMyScore",mainMyScore);

        List<TestScoreVO> subMyScores= testService.subTestMyScore(testScoreVO);
        model.addAttribute("subMyScores",subMyScores);

        for(TestScoreVO mySub : subMyScores){
            if(mySub.getSubTestNum()!=0){

                return "content/member/student_subject_check";
            }
        }


        return "content/member/student_test_check";
    }


    // 학생이 본인 성적 모든성적 조회 프린트

    @GetMapping("totalStuPrint")
    public String totalStuPrint(){
        return "content/member/student_total_test";
    }













}
