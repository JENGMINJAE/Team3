package com.green.Team3.board.service;


import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.SearchVO;

import java.util.List;

public interface BoardService {

    //다음에 INSERT 할 BOARD_NUM 조회
    int selectNextNoticeCode();

    //게시글 목록 조회
    List<BoardVO> selectNoticeList(SearchVO searchVO);
    
    //게시글 등록 - 공지사항 (게시글 + 이미지 등록)
    void insertNotice(BoardVO boardVO);

    //게시글 등록 - 문의사항
    void insertQna(BoardVO boardVO);

    //게시글 상세 조회
    BoardVO selectNoticeDetail(int boardNum);

    //게시글 상세 조회
    BoardVO selectQnaDetail(int boardNum);

    //게시글 조회수 증가
    void updateBoardCnt(int boardNum);


    //공지사항 이미지 삭제 ***********
//    void deleteImg(int boardNum);

    //게시글 삭제 - 공지사항 (게시글 + 이미지 삭제) - 트랜젝션 쓸 지 안쓸지 모르겠다;
    void deleteNotice(BoardVO boardVO);

    //이미지 첨부파일 있는지 여부 확인 ***********
//    boolean hasImg(int boardNum);



    //게시글 삭제 - 문의사항
    void deleteQna(int boardNum);

    //게시글 수정
    void updateBoard(BoardVO boardVO);

    //게시글 수 조회
    int selectNoticeCnt(SearchVO searchVO);

    //게시글 상세 - 이전글 조회
    BoardVO prevPage(int boardNum);

    //게시글 상세 - 다음글 조회
    BoardVO nextPage(int boardNum);



}
