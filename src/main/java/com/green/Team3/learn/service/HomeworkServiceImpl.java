package com.green.Team3.learn.service;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.learn.vo.HomeworkVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ClsVO> selectClassByThisTeacher(String memberId) {
        return sqlSession.selectList("learnMapper.selectClassByThisTeacher",memberId);
    }

    @Override
    public void homeworkAdd(HomeworkVO homeworkVO) {
        sqlSession.insert("learnMapper.homeworkAdd",homeworkVO);
    }
}
