package com.green.Team3.member.service;

import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //회원 가입
    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("member.join", memberVO);
    }

    //로그인
    @Override
    public MemberVO login(String memberId) {
        return sqlSession.selectOne("member.login", memberId);
    }

    // 전체 회원 목록 조회
    public List<MemberVO> selectMembers(SearchVO searchVO){
        return sqlSession.selectList("member.selectMembers", searchVO);
    }

    // 회원 상세 정보 목록 조회
    @Override
    public MemberVO memberDetail(MemberVO memberVO) {
        return sqlSession.selectOne("member.memberDetail", memberVO);
    }

    @Override
    public List<MemberVO> selectStudents() {
        return sqlSession.selectList("member.selectStudents");
    }

    //아이디 중복 확인
//    @Override
//    public int idCheck(MemberVO memberVO) {
////        int result = sqlSession.
////        return ;
//    }


}
