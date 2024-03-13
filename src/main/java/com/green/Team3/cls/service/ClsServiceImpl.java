package com.green.Team3.cls.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clsService")
public class ClsServiceImpl implements ClsService{
    @Autowired
    private SqlSessionTemplate sqlSession;
}
