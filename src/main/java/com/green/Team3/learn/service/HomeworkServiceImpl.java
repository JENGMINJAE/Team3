package com.green.Team3.learn.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    private SqlSessionTemplate sqlSession;
}
