package com.green.Team3.member.service;

import com.green.Team3.member.vo.MemberVO;

import java.util.List;

public interface MemberService {
    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);

    //아이디 중복 확인
//    int idCheck(MemberVO memberVO);


}
