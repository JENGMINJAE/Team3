package com.green.Team3.admin.service;

import com.green.Team3.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    SqlSessionTemplate sqlSession;

    // 강사 권한 수정
    @Override
    public void updateTeacher(MemberVO memberVO) {
        sqlSession.update("admin.updateTeacher", memberVO);
    }



}
