package com.green.Team3.cls.service;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clsService")
public class ClsServiceImpl implements ClsService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ClsVO> selectClass(MemberVO memberVO) {
        return sqlSession.selectList("clsMapper.selectClasses", memberVO);
    }
}
