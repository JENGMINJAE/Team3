package com.green.Team3.learn.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.vo.ConsultVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("consultService")
public class ConsultServiceImpl implements ConsultService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<OperatorVO> selectClassNumAndStuNum(int classNum) {
        return sqlSession.selectList("learnMapper.selectClassNumAndStuNum",classNum);
    }

    @Override
    public void insertConsult(ConsultVO vo) {
        sqlSession.insert("learnMapper.insertConsult",vo);
    }
}
