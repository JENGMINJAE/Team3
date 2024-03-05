package com.green.Team3.learn.service;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.learn.vo.HomeworkVO;

import java.util.List;

public interface HomeworkService {
    List<ClsVO> selectClassByThisTeacher(String memberId);
    void homeworkAdd(HomeworkVO homeworkVO);
}
