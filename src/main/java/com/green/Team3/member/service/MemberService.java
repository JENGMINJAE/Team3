package com.green.Team3.member.service;

import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.member.vo.MemberVO;

import java.util.List;

public interface MemberService {
    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(String memberId);

    // 회원 전체 목록 조회
    List<MemberVO> selectMembers(SearchVO searchVO);

    // 상세 회원 목록 조회 (관리자용)
    MemberVO memberDetail(MemberVO memberVO);

    // 학생 전체 목록 조회
    List<MemberVO> selectStudents();

    // 회원 이메일 조회
    String getMemberEmail(MemberVO memberVO);

    // 회원 비밀번호 업데이트
    void updateMemberPw(MemberVO memberVO);

    // 회원 상세 정보 조회(자신)
    MemberVO selectMyInformation(String memberId);

    // 패스워드 일치 확인
    String matchPassWord(String memberId);

    // 아이디 찾기
    String findMemberId(MemberVO memberVO);
}
