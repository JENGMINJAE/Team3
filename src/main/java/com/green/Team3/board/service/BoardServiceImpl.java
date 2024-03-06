package com.green.Team3.board.service;

import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    //다음에 INSERT 할 공지사항 BOARD_NUM 조회
    @Override
    public int selectNextNoticeCode() {
        return sqlSession.selectOne("board.selectNextNoticeCode");
    }

    //공지사항 목록 조회
    @Override
    public List<BoardVO> selectNoticeList(SearchVO searchVO) {
        List<BoardVO> list = sqlSession.selectList("board.selectNoticeList", searchVO);
        return list;
    }

    //공지사항 게시글 등록
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

    //공지사항 게시글 삭제
    @Override
    public void deleteNotice(int boardNum) {
        sqlSession.delete("board.deleteNotice", boardNum);
    }

    //공지사항 게시글 수 조회
    @Override
    public int selectNoticeCnt() {
        return sqlSession.selectOne("board.selectNoticeCnt");
    }


}
