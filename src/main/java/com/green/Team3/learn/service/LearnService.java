package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.AttendanceTypeVO;
import com.green.Team3.learn.vo.AttendanceVO;
import com.green.Team3.learn.vo.InsertAtdListVO;

import java.util.ArrayList;
import java.util.List;

public interface LearnService {
    List<AttendanceTypeVO> selectAtd();
    void insertAttendance(InsertAtdListVO vo);
}
