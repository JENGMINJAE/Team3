package com.green.Team3.member.service;

import com.green.Team3.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //회원 가입
    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("member.join", memberVO);
    }
}
