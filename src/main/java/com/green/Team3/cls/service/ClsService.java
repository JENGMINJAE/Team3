package com.green.Team3.cls.service;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;

import java.util.List;

public interface ClsService {

    // 선택한 회원의 수강목록 조회
    List<ClsVO> selectClass(MemberVO memberVO);
}
