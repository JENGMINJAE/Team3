package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestVO;

import java.util.List;

public interface TestService {

    // 강사 강의 목록조회
    List<ClsVO> selectTeacherClassList();

    // 반별 학생 평가명 점수 조회
    List<TestScoreVO> selectTestList(int classNum);

    // 평가명 추가
    void insertTest(TestVO testVO);

    // 평가명 목록 조회
    List<TestVO> selectTest(int classNum);

    // 성적입력시 학생이름 조회
    List<OperatorVO> selectStuName(int classNum);

    // class 명만 조회
    List<ClsVO> onlyClassNum(int classNum);

    // 테스트 검색란 에서 조회
    List<TestVO> searchTestList(SearchTestVO searchTestVO);

}
