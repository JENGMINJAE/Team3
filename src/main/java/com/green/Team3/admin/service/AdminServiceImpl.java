package com.green.Team3.admin.service;

import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    SqlSessionTemplate sqlSession;

    // 강사 전체 목록 조회
    @Override
    public List<TeacherVO> selectTeachers() {
        return sqlSession.selectList("teacher.selectTeachers");
    }
    // 강사 상세 정보 조회
    @Override
    public TeacherVO detailTeacher(TeacherVO teacherVO) {
        return sqlSession.selectOne("teacher.detailTeacher", teacherVO);
    }

    // 강사 권한 수정
    @Override
    public void updateTeacher(TeacherVO teacherVO) {
        sqlSession.update("admin.updateTeacher", teacherVO);
    }




}
