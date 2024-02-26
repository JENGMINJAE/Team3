package com.green.Team3.member.service;

import com.green.Team3.member.vo.MemberVO;

public interface MemberService {
    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);


}
