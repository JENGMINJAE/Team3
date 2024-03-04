package com.green.Team3.board.controller;

import com.green.Team3.board.service.BoardService;
import com.green.Team3.board.service.BoardServiceImpl;
import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "BoardService")
    private BoardServiceImpl boardService;

    // 공지사항 페이지
    @RequestMapping("/noticeList")
    public String List(Model model){
        // 공지사항 목록 조회
        List<BoardVO> noticeList = boardService.selectNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "content/common/notice_list";
    }

    // 공지사항 작성 페이지로 이동
    @GetMapping("/writeForm")
    public String noticeWriteForm(){
        return "content/common/notice_write_form";
    }

    // 공지사항 작성
    @PostMapping("/write")
    public String noticeWrite(BoardVO boardVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        boardVO.setMemberId(loginInfo.getMemberId());
        boardService.insertNotice(boardVO);
        return "redirect:/board/noticeList";
    }
    
    // 공지사항 상세 조회
    @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam(name = "boardNum") int boardNum
                                , Model model){
        //조회수 증가
        boardService.updateBoardCnt(boardNum);

        //상세 조회
        BoardVO vo = boardService.selectNoticeDetail(boardNum);
        model.addAttribute("board", vo);

        return "content/common/notice_detail";
    }






}
