package com.green.Team3.learn.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("consultService")
public class ConsultServiceImpl implements ConsultService{
    @Autowired
    private SqlSessionTemplate sqlSession;
}
