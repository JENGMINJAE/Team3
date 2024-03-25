package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;

import java.util.List;

public interface TestService {

    // 강사 강의 목록조회 ////
    List<ClsVO> selectTeacherClassList(String memberId);

//    // 수업듣는 학생인원 조회
//    List<ClsVO> selectClassStuCnt(String memberId);

    // 반별 학생 평가명 점수 조회
    List<TestScoreVO> selectTestList(int classNum);

    // 평가명 추가///
    void insertTest(TestVO testVO);

    // 평가명 목록 조회////
    List<TestVO> selectTest(int classNum);


    // 시험명 클릭시 학생별 성적 조회////
    List<TestScoreVO> selectTestScore(int testNum);

    // 테스트 검색란 에서 조회///
    List<TestVO> searchTestList(SearchTestVO searchTestVO);




    // 성적입력시 학생이름 조회 ////
    List<MemberVO> selectStuName(int classNum);

    // 점수 등록버튼 클릭시 저장////
    void insertStuScore(TestScoreVO testScoreVO);

    // class 명만 조회//
    ClsVO onlyClassNum(int classNum);


    // 시험테스트 번호로 학생명 조회///
    List<MemberVO> memNumInfo(int testNum);

    // 시험테스트 번호로 테스트명 조회
    TestVO testNumInfo(int testNum);


    // 수정 클릭 시 점수 업데이트//
    void updateScore(TestScoreVO testScoreVO);




    // 시험성적 통계 성적만 조회/////
    List<TestVO> totalTestSelect(int classNum);
    List<TestScoreVO> totalStuScoreSelect(int classNum);


    // ///////////////////////////////

    //  sub 조회
    List<TestSubjectVO> subSelect(int testNum);

    // 학생 수 조회
    List<MemberVO> stuCnt(int classNum);

    // 과목 저장
    void insertSub(TestSubjectVO testSubjectVO);

    // 과목 저장 할때 Test 만점없이
    void subMainTestInsert(TestVO testVO);

    // 과목 조회 하기
    List<TestSubjectVO> selectSubList(int testNum);

    // 테스트 만점 조회
   List<TestVO> testNumInfo2(int testNum);

    //과목저장
    void insertSubScore(TestScoreVO testScoreVO);

// /////////////////////////////////////////////////////////

    // 과목 1개 조회
    TestSubjectVO selectSubOne(int subTestNum);



    // 메인테스트full 상세정보 수정
    void updateTestDetail(TestVO testVO);
    // 메인테스트 만점 제외하고 수정
    void  updateTestDeTwo(TestVO testVO);


    // 과목 상세정보 수정
    void updateSubDetail(TestSubjectVO testSubjectVO);

    // ////////////////////////학생이 로그인했을때 성적 확인 ///////////////////////////

    // ####### 성적 조회 서비스 기능들 #######
    // 학생정보조회
    MemberVO selectStuTest();
    // 학생 수강별 시험목록조회
    List<TestVO> selectStuCLTest(String memberId);
    // 학생 시험지별 목록조회
    List<TestVO> selectStuTestDetail(String memberId);
    // 학생 과목별 목록조회
    List<TestSubjectVO> selectStuSub(String memberId);
    //학생 전체 성적이수표 조회
    List<OperatorVO> totalSelectTest(String memberId);

    //############ 조회버튼 클릭시 성적 상세페이지 이동하여 조회 ########
    // 학생 과목 없을시   my 성적페이지 이동 & 조회
    List<TestScoreVO> mainTestMyScore(TestScoreVO testScoreVO);
    // 학생 과목 있을시   my 성적페이지 이동 & 조회
    List<TestScoreVO> subTestMyScore(TestScoreVO testScoreVO);

}
