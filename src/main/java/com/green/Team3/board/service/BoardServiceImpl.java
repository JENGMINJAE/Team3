package com.green.Team3.board.service;

import com.green.Team3.board.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    //공지사항 목록 조회
    @Override
    public List<BoardVO> selectNoticeList() {
        List<BoardVO> list = sqlSession.selectList("selectNoticeList");
        return list;
    }

    //

}
