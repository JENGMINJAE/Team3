package com.green.Team3.board.controller;

import com.green.Team3.board.service.ReplyServiceImpl;
import com.green.Team3.board.vo.ReplyVO;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    //댓글 등록
    @PostMapping("/replyReg")
    public String regRely(ReplyVO replyVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        replyVO.setMemberId(loginInfo.getMemberId());
        replyService.insertReply(replyVO);

        return "redirect:/board/qnaDetail?boardNum=" + replyVO.getBoardNum();
    }



}
