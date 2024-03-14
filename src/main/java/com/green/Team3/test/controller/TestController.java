package com.green.Team3.test.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/test")
@Controller
public class TestController {

    @Resource(name = "testService")
  private TestServiceImpl testService;


    //http://127.0.0.1:8081/test/testList
    // 강사가 수업하는 수강명 목록 조회
    @GetMapping("/testList")
    public String testList(Model model){
        List<ClsVO> classList = testService.selectTeacherClassList();
        System.out.println(classList);

        model.addAttribute("classList", classList);

        return "content/test/teacher_first_sc";
        //return "content/test/pr_list";
    }



    @RequestMapping("/scoreTeacher")
    public String scoreTeacher(Model model,
                               OperatorVO sstudentVO, TestVO testVO,
                               @RequestParam(name="classNum") int classNum,
                               SearchTestVO searchTestvo) {



        // 반 학생 성적정보 페이지에서 선택할 평가명 조회
        //반 번호 넘기기 그래야 평가명 추가 버튼 클릭 가능
        List<ClsVO> onlyClassNum = testService.onlyClassNum(classNum);
        model.addAttribute("onlyClassNum",onlyClassNum);
        System.out.println(onlyClassNum);


        List<TestVO> selectTestNames = testService.selectTest(testVO.getClassNum());
        model.addAttribute("selectTestNames", selectTestNames);


        //  반 학생명 조회
        List<OperatorVO> selectStuNames = testService.selectStuName(sstudentVO.getClassNum());
        model.addAttribute("selectStuNames", selectStuNames);
        System.out.println("안읽니!!!!!!111" + selectStuNames);


        // 검색란
        List<TestVO> searchTests = testService.searchTestList(searchTestvo);
        model.addAttribute("searchTests",searchTests);
        System.out.println(searchTests);



        return "content/item/teacher_in";


    }





}
