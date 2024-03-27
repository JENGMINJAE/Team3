package com.green.Team3.admin.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdminService {

    // 선생님 전체 목록 조회
    List<TeacherVO> selectTeachers();

    // 선생님 상세 목록 조회
    List<ClsVO> detailTeacher(int teacherNum);

    // 회원 권한 수정
    void updateRoll(MemberVO memberVO);

    // 강사 재직 상태 수정
    void changeAttendance(TeacherVO teacherVO);

    // MEMBER_ROLL 리스트 조회
    List<MemberVO> rollList();

    // 인적 사항 정보 변경
    void changePersonalInfo(MemberVO memberVO);

    // 반 생성
    void makeCls(ClsVO clsVO);

    // 반정보 수정
    void updateClassInfo(ClsVO clsVO);

    // 선생님 이름 조회
    List<TeacherVO> selectTeacherName();

    // 반에서 정보 수정
    int updateClass(ClsVO clsVO);

    // 결제 요청 시
    List<ClsVO> requestPayInfo(OperatorVO operatorVO);

    // 수강 신청 모달 열 때
    List<ClsVO> regClasses(OperatorVO operatorVO);

    // 수강 신청 시 insert 문
//    void insertOperator(OperatorVO operatorVO);

    // 결제 성공 시 update
    void successPayment(OperatorVO operatorVO);


}
