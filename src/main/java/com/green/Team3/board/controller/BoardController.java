package com.green.Team3.board.controller;

import com.green.Team3.board.service.BoardService;
import com.green.Team3.board.service.BoardServiceImpl;
import com.green.Team3.board.service.ReplyServiceImpl;
import com.green.Team3.board.utill.UploadUtil;
import com.green.Team3.board.vo.*;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;


    ///////////////////////////////// 공지 사항 /////////////////////////////////////

    // 공지사항 페이지
    @RequestMapping("/noticeList")
    public String List(SearchVO searchVO, Model model
                    , @RequestParam(name = "searchValue" ,required = false) String searchValue
                    , @RequestParam(name = "searchType" ,required = false) String searchType){
        // 공지사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);

        // 공지사항 목록 조회
        List<BoardVO> noticeList = boardService.selectNoticeList(searchVO);
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);

        return "content/common/notice_list";
    }

    // 공지사항 작성 페이지로 이동
    @GetMapping("/noticeWriteForm")
    public String noticeWriteForm(){
        return "content/common/notice_write_form";
    }

//    공지사항 작성 원본
//    // 공지사항 게시글 작성
//    @PostMapping("/noticeWrite")
//    public String noticeWrite(BoardVO boardVO, HttpSession session){
//        //로그인 정보
//        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
//
//        //공지사항 등록 쿼리
//        boardVO.setMemberId(loginInfo.getMemberId());
//        boardService.insertNotice(boardVO);
//
//        return "redirect:/board/noticeList";
//    }


    // 공지사항 게시글 작성 - 이미지 첨부 기능 추가 중 ㅠㅠ
    @PostMapping("/noticeWrite")
    public String noticeWrite(BoardVO boardVO
                            , Authentication authentication
                            , @RequestParam(name = "subImgs") MultipartFile[] subImgs){

        //------------------- 사용자 로그인 정보 받아오기 --------------------
        User user = (User) authentication.getPrincipal();
        boardVO.setMemberId(user.getUsername());

        //----------------------- 파일 첨부 기능 -----------------------
        //첨부 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs);

        //다음에 들어갈 boardNum 조회
        int boardNum = boardService.selectNextNoticeCode();

        //------------------------ 공지사항 등록 ------------------------
        boardVO.setBoardNum(boardNum);

        //------------------------ 파일 첨부 등록 -----------------------
        boardVO.setImgList(imgList);

        System.out.println(boardVO);
        //쿼리 실행
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
        System.out.println(vo);
        model.addAttribute("notice", vo);

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(boardNum);
        if (prevPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("prevPage", prevPage);
        }
        //이전 글이 없을 때
        else {
            model.addAttribute("prevPageNotFound", true);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardNum);
        if(nextPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("nextPage", nextPage);
        }
        //다음 글이 없을 때
        else {
            model.addAttribute("nextPageNotFound", true);
        }

        return "content/common/notice_detail";
    }

//    @GetMapping("/deleteNotice")
//    public String deleteNotice(@RequestParam(name = "boardNum") int boardNum){
//        boardService.deleteNotice(boardNum);
//        return "redirect:/board/noticeList";
//    }


    // 공지사항 게시글 삭제 ************************ 첨부파일이 있을 떄 / 없을 때
    @GetMapping("/deleteNotice")
    public String deleteNotice(BoardVO boardVO){

        boardService.deleteNotice(boardVO);
        return "redirect:/board/noticeList";
    }

    // 공지사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateNotice")
    public String update(@RequestParam(name = "boardNum", required=false) int boardNum, Model model){
        model.addAttribute("notice", boardService.selectNoticeDetail(boardNum));
        return "content/common/notice_update";
    }

    //공지사항 게시글 수정
    @PostMapping("/updateNotice")
    public String update(BoardVO boardVO, @RequestParam("boardNum") int boardNum){
        boardVO.setBoardNum(boardNum); // boardNum 설정
        boardService.updateBoard(boardVO);
        return "redirect:/board/noticeDetail?boardNum=" + boardNum; // boardNum 추가
    }


    ///////////////////////////////// 문의 사항 /////////////////////////////////////

    // 문의사항 페이지
    @RequestMapping("/qnaList")
    public String qnaList(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType){
        // 문의사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);

        // 문의사항 목록 조회
        List<BoardVO> qnaList = boardService.selectNoticeList(searchVO);
        model.addAttribute("qnaList", qnaList);
        // 문의사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 문의사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);

        return "content/common/qna_list";
    }

    // 공지사항 작성 페이지로 이동
    @GetMapping("/qnaWriteForm")
    public String qnaWriteForm(){
        return "content/common/qna_write_form";
    }

    // 문의사항 게시글 작성
    @PostMapping("/qnaWrite")
    public String qnaWrite(BoardVO boardVO, HttpSession session){
        //로그인 정보
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        //문의사항 게시글 등록
        boardVO.setMemberId(loginInfo.getMemberId());
        boardService.insertQna(boardVO);
        return "redirect:/board/qnaList";
    }

    // 문의사항 상세 조회
    @GetMapping("/qnaDetail")
    public String qnaDetail(@RequestParam(name = "boardNum") int boardNum
            , Model model){
        //조회수 증가
        boardService.updateBoardCnt(boardNum);
        System.out.println(boardNum);

        //상세 조회
        BoardVO vo = boardService.selectQnaDetail(boardNum);
        model.addAttribute("qna", vo);

        //댓글 조회
        List<ReplyVO> replyList = replyService.selectReplyList(boardNum);
        model.addAttribute("replyList", replyList);

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(boardNum);
        if (prevPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("prevPage", prevPage);
        }
        //이전 글이 없을 때
        else {
            model.addAttribute("prevPageNotFound", true);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardNum);
        if(nextPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("nextPage", nextPage);
        }
        //다음 글이 없을 때
        else {
            model.addAttribute("nextPageNotFound", true);
        }

        return "content/common/qna_detail";
    }

    // 문의사항 게시글 삭제
    @GetMapping("/deleteQna")
    public String deleteQna(@RequestParam(name = "boardNum") int boardNum){
        boardService.deleteQna(boardNum);
        return "redirect:/board/qnaList";
    }

    // 문의사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateQna")
    public String updateQna(@RequestParam(name = "boardNum", required=false) int boardNum, Model model){
        model.addAttribute("qna", boardService.selectNoticeDetail(boardNum));
        return "content/common/qna_update";
    }

    //문의사항 게시글 수정
    @PostMapping("/updateQna")
    public String updateQna(BoardVO boardVO, @RequestParam("boardNum") int boardNum){
        boardVO.setBoardNum(boardNum);
        boardService.updateBoard(boardVO);
        return "redirect:/board/qnaDetail?boardNum=" + boardNum;
    }






}
