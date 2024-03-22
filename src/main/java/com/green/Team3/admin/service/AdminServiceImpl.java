package com.green.Team3.admin.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    SqlSessionTemplate sqlSession;

    // 강사 전체 목록 조회 (완료)
    @Override
    public List<TeacherVO> selectTeachers() {
        return sqlSession.selectList("teacher.selectTeachers");
    }

    // 강사 상세 정보 조회 (완료)
    @Override
    public List<ClsVO> detailTeacher(int teacherNum) {
        return sqlSession.selectList("teacher.detailTeacher", teacherNum);
    }

    // 회원 권한 수정 (완료)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoll(MemberVO memberVO) {
        sqlSession.update("admin.updateRoll", memberVO);
        // memberRoll : 2 (강사)일 경우, 강사 테이블 생성
        if(memberVO.getMemberRoll() == 2) {
            sqlSession.insert("admin.insertTeacher", memberVO);
        }
    }

    // 강사 재직 상태 수정 (완료)
    @Override
    public void changeAttendance(TeacherVO teacherVO) {
        sqlSession.update("admin.changeAttendance", teacherVO);
    }

    // memberRoll 전체 목록 조회 (완료)
    @Override
    public List<MemberVO> rollList() {
        return sqlSession.selectList("member.rollList");
    }

    // 인적 사항 정보 변경 (완료)
    @Override
    public void changePersonalInfo(MemberVO memberVO) {
        sqlSession.update("admin.changeMemberData", memberVO);
    }

    // 반 생성
    @Override
    public void makeCls(ClsVO clsVO) {
        sqlSession.insert("admin.makeCls", clsVO);
    }

    // 반정보 수정
    @Override
    public void updateClassInfo(ClsVO clsVO) {
        sqlSession.update("clsMapper.updateClass", clsVO);
    }

    // 강사 목록 조회
    @Override
    public List<TeacherVO> selectTeacherName() {
        return sqlSession.selectList("admin.selectTeacherName");
    }

    @Override
    public int updateClass(ClsVO clsVO) {
        return sqlSession.update("admin.updateClass", clsVO);
    }

    // 결제 요청 시
    @Override
    public List<ClsVO> requestPayInfo(String memberId) {
        return sqlSession.selectList("member.requestPayInfo", memberId);
    }

    // 결제 승인 시
    @Override
    public void successPayment(OperatorVO operatorVO) {
        sqlSession.insert("admin.successPayment", operatorVO);
    }

}
