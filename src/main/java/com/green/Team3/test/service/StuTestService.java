package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;

import java.util.List;

public interface StuTestService {

    // ////////////////////// 진행중 /////////////////////////


    // ########################  학생이 로그인했을때 성적 확인  ########################

    // ####### 성적 조회 서비스 기능들 #######

    // 학생정보조회
    MemberVO selectStuTest(String memberId);

    // 학생 수강별 시험목록조회
    List<TestVO> selectStuCLTest(String memberId);

    // 학생 기간별 목록조회
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