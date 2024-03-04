package com.green.Team3.admin.service;

import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;

import java.util.List;

public interface AdminService {

    // 선생님 전체 목록 조회
    List<TeacherVO> selectTeachers();

    // 선생님 상세 목록 조회
    TeacherVO detailTeacher(TeacherVO teacherVO);

    // 강사 권한 수정
    void updateTeacher(TeacherVO teacherVO);
}