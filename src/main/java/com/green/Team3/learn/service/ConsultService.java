package com.green.Team3.learn.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.vo.ConsultVO;

import java.util.List;

public interface ConsultService {
    List<OperatorVO> selectClassNumAndStuNum(int classNum);
    void insertConsult(ConsultVO vo);
}
