package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
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

    // 강사 강의 목록조회
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

    // 평가명 목록 조회
    @Override
    public List<TestVO> selectTest(int classNum) {
        return sqlSession.selectList("testMapper.selectTest", classNum);
    }


    // 학생이름 조회
    @Override
    public List<OperatorVO> selectStuName(int classNum) {
        return sqlSession.selectList("testMapper.selectStuName", classNum);
    }

    // class 명만 조회
    @Override
    public List<ClsVO> onlyClassNum(int classNum) {
        return sqlSession.selectList("testMapper.selectTeacherClassList", classNum);
    }

    // 검색란
    @Override
    public List<TestVO> searchTestList(SearchTestVO searchTestVO) {
        return sqlSession.selectList("testMapper.searchTestList", searchTestVO);
    }
}
