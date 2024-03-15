package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.SearchTestVO;
import com.green.Team3.test.vo.TestScoreVO;
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
    public List<ClsVO> selectTeacherClassList() {
        return sqlSession.selectList("testMapper.selectTeacherClassList");
    }

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
    public List<ClsVO> onlyClassNum(int classNum) {
        return sqlSession.selectList("testMapper.selectTeacherClassList", classNum);
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
}
