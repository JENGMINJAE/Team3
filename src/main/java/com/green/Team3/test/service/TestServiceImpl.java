package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements TestService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 강사 강의 목록조회 //
    @Override
    public List<ClsVO> selectTeacherClassList(String memberId) {
        return sqlSession.selectList("testMapper.selectTeacherClassList",memberId);
    }

//    // 수업듣는 학생인원 조회
//    @Override
//    public List<ClsVO> selectClassStuCnt(String memberId) {
//        return sqlSession.selectList("testMapper.selectClassStuCnt", memberId);
//    }

    // 반별 학생들의 평가명 점수 조회
    @Override
    public List<TestScoreVO> selectTestList(int classNum) {
        return sqlSession.selectList("testMapper.selectTestList",classNum);
    }

    // 평가명 추가
    @Override
    public void insertTest(TestVO testVO) {
        sqlSession.selectOne("testMapper.insertTest", testVO);
    }

    // 평가명 목록 조회 //
    @Override
    public List<TestVO> selectTest(int classNum) {
        return sqlSession.selectList("testMapper.selectTest", classNum);
    }


    // 시험명 클릭시 학생별 성적 조회
    @Override
    public List<TestScoreVO> selectTestScore(int testNum) {
        return sqlSession.selectList("testMapper.selectTestScore", testNum);
    }

    // 검색란
    @Override
    public List<TestVO> searchTestList(SearchTestVO searchTestVO) {
        return sqlSession.selectList("testMapper.searchTestList", searchTestVO);
    }

// /////////////////////////////////////////



    // 학생이름 조회 //
    @Override
    public List<MemberVO> selectStuName(int classNum) {
        return sqlSession.selectList("testMapper.selectStuName", classNum);
    }
    // 점수 등록버튼 클릭시 저장
    @Override
    public void insertStuScore(TestScoreVO testScoreVO) {
        sqlSession.insert("testMapper.insertStuScore", testScoreVO);
    }


    // class 명만 조회
    @Override
    public ClsVO onlyClassNum(int classNum) {
        return sqlSession.selectOne("testMapper.onlyClassNum", classNum);
    }


    // 시험테스트 번호로 반학생 조회
    @Override
    public List<MemberVO> memNumInfo(int testNum) {
        return  sqlSession.selectList("testMapper.memNumInfo", testNum);
    }

    // 시험테스트 번호로 테스트명 조회
    @Override
    public TestVO testNumInfo(int testNum) {
        return sqlSession.selectOne("testMapper.testNumInfo", testNum);
    }

    // 수정 클릭 시 점수 업데이트
    @Override
    public void updateScore(TestScoreVO testScoreVO) {
        sqlSession.update("testMapper.updateScore", testScoreVO);
    }





    // ///////시험성적 통계 성적만 조회
    @Override
    public List<TestVO> totalTestSelect(int classNum) {
        return sqlSession.selectList("testMapper.totalTestSelect", classNum);
    }
    @Override
    public List<TestScoreVO> totalStuScoreSelect(int classNum) {
        return sqlSession.selectList("testMapper.totalStuScoreSelect", classNum);
    }



    // /////////////////////////////

    // 과목조회
    @Override
    public List<TestSubjectVO> subSelect(int testNum) {
        return sqlSession.selectList("testMapper.subSelect",testNum);
    }
    // 학생 수 조회
    @Override
    public List<MemberVO> stuCnt(int classNum) {
        return sqlSession.selectList("testMapper.stuCnt",classNum);
    }

    // 과목 저장
    @Override
    public void insertSub(TestSubjectVO testSubjectVO) {
        sqlSession.insert("testMapper.insertSub", testSubjectVO);
    }

    // 과목 저장 할때 Test 만점없이
    @Override
    public void subMainTestInsert(TestVO testVO) {
        sqlSession.insert("testMapper.subMainTestInsert", testVO);
    }

    // 과목 조회 하기
    @Override
    public List<TestSubjectVO> selectSubList(int testNum) {
        return sqlSession.selectList("testMapper.selectSubList", testNum);
    }
    // 테스트 만점 조회
    @Override
    public List<TestVO> testNumInfo2(int testNum) {
        return sqlSession.selectList("testMapper.testNumInfo2", testNum);
    }

    //과목저장
    @Override
    public void insertSubScore(TestScoreVO testScoreVO) {
        sqlSession.insert("testMapper.insertSubScore", testScoreVO);
    }




    // /////////////////////////

    // 과목 1개 상세정보  조회
    @Override
    public TestSubjectVO selectSubOne(int subTestNum) {
        return sqlSession.selectOne("testMapper.selectSubOne", subTestNum);
    }


    // /////////////////////////////////////////////////////

    // 메인테스트 full 상세정보 수정
    @Override
    public void updateTestDetail(TestVO testVO) {
        sqlSession.update("testMapper.updateTestDetail",testVO);
    }

    // 메인테스트 만점제외 수정
    @Override
    public void updateTestDeTwo(TestVO testVO) {
        sqlSession.update("testMapper.updateTestDeTwo",testVO);
    }

    // 과목 상세정보 수정
    @Override
    public void updateSubDetail(TestSubjectVO testSubjectVO) {
        sqlSession.update("testMapper.updateSubDetail",testSubjectVO);
    }

    // ////////////////////////학생이 로그인했을때 성적 확인 ///////////////////////////
    // 서치 학생정보조회
    @Override
    public MemberVO selectStuTest() {
        return sqlSession.selectOne("testMapper.selectStuTest");
    }
    // 서치 학생 수강별 시험목록조회
    @Override
    public List<TestVO> selectStuCLTest(String memberId) {
        return sqlSession.selectList("testMapper.selectStuCLTest", memberId);
    }
    // 서치 학생 시험지별 목록조회
    @Override
    public List<TestVO> selectStuTestDetail(String memberId) {
        return sqlSession.selectList("testMapper.selectStuTestDetail", memberId);
    }
    // 서치 학생 과목별 목록조회
    @Override
    public List<TestSubjectVO> selectStuSub(String memberId) {
        return sqlSession.selectList("testMapper.selectStuSub", memberId);
    }
    // 서치 학생 전체 성적이수표 조회
    @Override
    public List<OperatorVO> totalSelectTest(String memberId) {
        return sqlSession.selectList("testMapper.totalSelectTest", memberId);
    }

    //############ 조회버튼 클릭시 성적 상세페이지 이동하여 조회 ########
    // 학생 과목 없을시  my 성적페이지 이동 & 조회
    @Override
    public List<TestScoreVO> mainTestMyScore(TestScoreVO testScoreVO) {
        return sqlSession.selectList("testMapper.mainTestMyScore", testScoreVO);
    }
    // 학생 과목 있을시   my 성적페이지 이동 & 조회
    @Override
    public List<TestScoreVO> subTestMyScore(TestScoreVO testScoreVO) {
        return sqlSession.selectList("testMapper.subTestMyScore", testScoreVO);
    }


}
