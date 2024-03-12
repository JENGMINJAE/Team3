package com.green.Team3.board.service;

import com.green.Team3.board.vo.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //댓글 작성
    @Override
    public void insertReply(ReplyVO replyVO){
        sqlSession.insert("reply.insertReply", replyVO);
    }

    //댓글 조회
    @Override
    public List<ReplyVO> selectReplyList(int boardNum) {
        return sqlSession.selectList("reply.selectReplyList", boardNum);
    }


}
