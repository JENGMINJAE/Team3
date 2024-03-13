package com.green.Team3.board.service;

import com.green.Team3.board.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    //댓글 작성
    void insertReply(ReplyVO replyVO);

    //댓글 조회
    List<ReplyVO> selectReplyList (int boardNum);


}
