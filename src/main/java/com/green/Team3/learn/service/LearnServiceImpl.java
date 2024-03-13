package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.AttendanceTypeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("learnService")
public class LearnServiceImpl implements LearnService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<AttendanceTypeVO> selectAtd() {
        return sqlSession.selectList("learnMapper.selectAtd");
    }
}
