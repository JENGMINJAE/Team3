package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuTestService")
public class StuTestServiceImpl implements StuTestService {


    @Autowired
    private SqlSessionTemplate sqlSession;

            // /////////////////// 진행중

    // ##################################### 학생이 로그인했을때 성적 확인 #####################################
            // 서치 학생정보조회
            @Override
            public MemberVO selectStuTest(String memberId) {
                return sqlSession.selectOne("testMapper.selectStuTest", memberId);
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
            public List<ClsVO> totalSelectTest(String memberId) {
                return sqlSession.selectList("testMapper.totalSelectTest", memberId);
            }

//############ 조회버튼 클릭시 성적 상세페이지 이동하여 조회 ########
            // 학생 과목 없을시  my 성적페이지 이동 & 조회
            @Override
            public TestScoreVO mainTestMyScore(TestScoreVO testScoreVO) {
                return sqlSession.selectOne("testMapper.mainTestMyScore", testScoreVO);
            }
            // 학생 과목 있을시   my 성적페이지 이동 & 조회
            @Override
            public List<TestScoreVO> subTestMyScore(TestScoreVO testScoreVO) {
                return sqlSession.selectList("testMapper.subTestMyScore", testScoreVO);
            }
}
