package com.green.Team3.board.controller;

import com.green.Team3.board.service.ReplyService;
import com.green.Team3.board.service.ReplyServiceImpl;
import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.ReplyVO;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //댓글 삭제
    @GetMapping("/deleteReply")
    public String deleteReply(@RequestParam(name="replyNum") int replyNum, ReplyVO replyVO){
        replyService.deleteReply(replyNum);
        return "redirect:/board/qnaDetail?boardNum=" + replyVO.getBoardNum();
    }

    //댓글 수정 - 비동기
    @ResponseBody
    @PostMapping("/updateReply")
    public String updateReply(ReplyVO replyVO){
        replyService.updateReply(replyVO);

        return replyService.reSelect(replyVO.getReplyNum());
    }




}
