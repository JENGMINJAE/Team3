package com.green.Team3.board.service;


import com.green.Team3.board.vo.BoardVO;

import java.util.List;

public interface BoardService {
    //공지사항 목록 조회
    List<BoardVO> selectNoticeList();
    
    //공지사항 등록
    void insertNotice(BoardVO boardVO);



}
