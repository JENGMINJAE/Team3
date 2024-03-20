package com.green.Team3.learn.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.member.vo.TeacherVO;

import java.util.List;

public interface ConsultService {
    List<OperatorVO> selectClassNumAndStuNum(int classNum);
    void insertConsult(ConsultVO vo);
    List<ConsultVO> selectEndConsultList(int teacherNum);
    List<ConsultVO> selectWillConsultList(int teacherNum);
    List<ConsultVO> selectTodayConsultList(int teacherNum);
    int selectTeacherNumOfMemberId(String memberId);

}
