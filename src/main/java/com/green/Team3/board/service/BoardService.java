package com.green.Team3.board.service;


import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.SearchVO;

import java.util.List;

public interface BoardService {
    //다음에 INSERT 할 공지사항 BOARD_NUM 조회
    int selectNextNoticeCode();

    //공지사항 목록 조회
    List<BoardVO> selectNoticeList(SearchVO searchVO);
    
    //공지사항 게시글 등록
    void insertNotice(BoardVO boardVO);

    //공지사항 상세 조회
    BoardVO selectNoticeDetail(int boardNum);

    //공지사항 조회수 증가
    void updateBoardCnt(int boardNum);

    //공지사항 게시글 삭제
    void deleteNotice(int boardNum);

    //공지사항 게시글 수 조회
    int selectNoticeCnt();




}
