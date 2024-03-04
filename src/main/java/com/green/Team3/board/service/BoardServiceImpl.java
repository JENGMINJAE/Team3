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
        List<BoardVO> list = sqlSession.selectList("board.selectNoticeList");
        return list;
    }

    //공지사항 등록
    @Override
    public void insertNotice(BoardVO boardVO) {
       sqlSession.insert("board.insertNotice", boardVO);
    }

    //공지사항 상세 조회
    @Override
    public BoardVO selectNoticeDetail(int boardNum) {
        BoardVO result = sqlSession.selectOne("board.selectNoticeDetail", boardNum);
        return result;
    }

    //공지사항 조회수 증가
    @Override
    public void updateBoardCnt(int boardNum) {
        sqlSession.update("board.updateBoardCnt", boardNum);

    }


}
