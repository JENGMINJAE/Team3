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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    ///////////////////////////////// 공지 사항 /////////////////////////////////////

    // 공지사항 - 학사공지 목록 페이지
    @RequestMapping("/noticeListStu")
    public String List1(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 공지사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();

        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> noticeList = boardService.selectNoticeListStu(searchVO);

        if (isSearch == 1 && totalDataCnt > 0) {
            // 검색된 전체 데이터 수를 페이지 정보에 반영
            searchVO.setTotalDataCnt(totalDataCnt);
            searchVO.setPageInfo();
        } else if (isSearch == 1 && totalDataCnt == 0) {
            isSearch = 2; // 검색 결과 없음
        }

        model.addAttribute("isSearch", isSearch);
        // 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/notice_list_stu";
    }

    // 공지사항 - 강사공지 목록 페이지
    @RequestMapping("/noticeListTea")
    public String List2(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 공지사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();

        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> noticeList = boardService.selectNoticeListTea(searchVO);

        if (isSearch == 1 && totalDataCnt > 0) {
            searchVO.setTotalDataCnt(totalDataCnt);
            searchVO.setPageInfo();
        } else if (isSearch == 1 && totalDataCnt == 0) {
            isSearch = 2; // 검색 결과 없음
        }

        model.addAttribute("isSearch", isSearch);
        // 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/notice_list_tea";
    }

    // 공지사항 작성 페이지로 이동
    @GetMapping("/noticeWriteForm")
    public String noticeWriteForm(){
        return "content/common/notice_write_form";
    }

    // 공지사항 게시글 작성 + 이미지 첨부 기능
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

        if (boardVO.getTypeNum() == 1) {
            return "redirect:/board/noticeListStu?typeNum=1";
        } else if (boardVO.getTypeNum() == 2) {
            return "redirect:/board/noticeListTea?typeNum=2";
        } else {
            return "redirect:/error";
        }
    }


    // 공지사항 상세 조회
    @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam(name = "boardNum") int boardNum
                                , @RequestParam(name = "typeNum") int typeNum
                                , Model model){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNum(boardNum);
        boardVO.setTypeNum(typeNum);

        //조회수 증가
        boardService.updateBoardCnt(boardVO.getBoardNum());

        //상세 조회
        BoardVO vo = boardService.selectNoticeDetail(boardVO);
        model.addAttribute("notice", vo);
        System.out.println(vo.getBoardTitle());

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(boardVO);
        System.out.println(prevPage + "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (prevPage.getBoardNum() != 0){
            model.addAttribute("prevPageNotFound", false);
            prevPage.setTypeNum(typeNum);
            model.addAttribute("prevPage", prevPage);

        } else {
            model.addAttribute("prevPageNotFound", true);
            prevPage.setTypeNum(typeNum);
            model.addAttribute("prevPage", prevPage);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardVO);
        System.out.println(nextPage + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (nextPage.getBoardNum() != 0){
            model.addAttribute("nextPageNotFound", false);
            nextPage.setTypeNum(typeNum);
            model.addAttribute("nextPage", nextPage);
        } else {
            nextPage.setTypeNum(typeNum);
            model.addAttribute("nextPage", nextPage);
            model.addAttribute("nextPageNotFound", true);
        }
        return "content/common/notice_detail";
    }

    // 공지사항 게시글 삭제(첨부 파일 있을 때 / 없을 때 모두 가능)
    @GetMapping("/deleteNotice")
    public String deleteNotice(BoardVO boardVO){
        boardService.deleteNotice(boardVO);
        if (boardVO.getTypeNum() == 1) {
            return "redirect:/board/noticeListStu?typeNum=1";
        } else if (boardVO.getTypeNum() == 2) {
            return "redirect:/board/noticeListTea?typeNum=2";
        } else {
            return "redirect:/error";
        }
    }

    // 공지사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateNotice")
    public String update(@RequestParam(name = "boardNum", required=false) int boardNum,
                         @RequestParam(name = "typeNum", required = false) int typeNum, Model model){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNum(boardNum);
        boardVO.setTypeNum(typeNum);
        model.addAttribute("notice", boardService.selectNoticeDetail(boardVO));
        return "content/common/notice_update";
    }

    //공지사항 게시글 수정 시 첨부파일 이미지 삭제(비동기)
    @ResponseBody
    @PostMapping("/deleteImgFile")
    public BoardVO deleteImgFile(@RequestParam(name="imgNum") int imgNum, @RequestParam(name="boardNum") int boardNum,@RequestParam(name = "typeNum")int typeNum){
        BoardVO boardVO = new BoardVO();
        boardVO.setTypeNum(typeNum);
        boardVO.setBoardNum(boardNum);
        boardService.deleteImgFile(imgNum);
        return boardService.selectNoticeDetail(boardVO);
    }


    // 공지사항 게시글 수정 + 이미지 첨부 기능 *******************************************************구현중
    @PostMapping("/updateNotice")
    public String updateNotice(BoardVO boardVO
                            , @RequestParam(name="boardNum") int boardNum
                            , @RequestParam(name = "subImgs") MultipartFile[] subImgs
                            , Authentication authentication){
        //----------------------- 파일 첨부 기능 -----------------------
        //첨부 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs);

        //글번호 세팅
        boardVO.setBoardNum(boardNum);

        //------------------------ 파일 첨부 등록 -----------------------
        // 새로운 첨부파일이 있는 경우
        if (subImgs != null && subImgs.length > 0) {
            // 새로운 첨부 파일 업로드
            List<ImgVO> newImgList = UploadUtil.multiUploadFile(subImgs);
            // 기존 첨부파일 삭제 + 새로운 첨부 파일 추가
            boardVO.setImgList(newImgList);
        }

        boardVO.setImgList(imgList);
        System.out.println(boardVO);

        //-------------------------공지사항 수정 쿼리 실행
        boardService.updateNotice(boardVO);
        return "redirect:/board/noticeDetail?boardNum=" + boardNum + "&typeNum=" + boardVO.getTypeNum();
    }

    ///////////////////////////////// 문의 사항 /////////////////////////////////////

    // 문의사항 페이지 - 공지사항이랑 분리
    @RequestMapping("/qnaList")
    public String qnaList(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 문의사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();

        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> qnaList = boardService.selectQnaList(searchVO);

        if (isSearch == 1 && totalDataCnt > 0) {
            // 검색된 전체 데이터 수를 페이지 정보에 반영
            searchVO.setTotalDataCnt(totalDataCnt);
            searchVO.setPageInfo();
        } else if (isSearch == 1 && totalDataCnt == 0) {
            isSearch = 2; // 검색 결과 없음
        }

        model.addAttribute("isSearch", isSearch);
        // 문의사항 목록 조회
        model.addAttribute("qnaList", qnaList);
        // 문의사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 문의사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/qna_list";
    }

    // 문의사항 작성 페이지로 이동
    @GetMapping("/qnaWriteForm")
    public String qnaWriteForm(){
        return "content/common/qna_write_form";
    }

    // 문의사항 게시글 작성
    @PostMapping("/qnaWrite")
    public String qnaWrite(BoardVO boardVO, Authentication authentication) {
        //로그인 정보
        User user = (User) authentication.getPrincipal();
        boardVO.setMemberId(user.getUsername());
        //문의사항 게시글 등록
        boardService.insertQna(boardVO);

        if (boardVO.getTypeNum() == 3) {
            return "redirect:/board/qnaList?typeNum=3";
        } else {
            return "redirect:/error";
        }
    }


    // 문의사항 상세 조회
    @GetMapping("/qnaDetail")
    public String qnaDetail(@RequestParam(name = "boardNum") int boardNum,
                            @RequestParam(name = "typeNum") int typeNum
                            , Model model){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNum(boardNum);
        boardVO.setTypeNum(typeNum);

        //조회수 증가
        boardService.updateBoardCnt(boardVO.getBoardNum());

        //상세 조회
        BoardVO vo = boardService.selectQnaDetail(boardVO.getBoardNum());
        model.addAttribute("qna", vo);
        System.out.println(vo.getBoardTitle());

        //댓글 조회
        List<ReplyVO> replyList = replyService.selectReplyList(boardVO.getBoardNum());
        model.addAttribute("replyList", replyList);

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(boardVO);
        System.out.println(prevPage + "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (prevPage.getBoardNum() != 0){
            model.addAttribute("prevPageNotFound", false);
            prevPage.setTypeNum(typeNum);
            model.addAttribute("prevPage", prevPage);

        } else {
            model.addAttribute("prevPageNotFound", true);
            prevPage.setTypeNum(typeNum);
            model.addAttribute("prevPage", prevPage);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardVO);
        System.out.println(nextPage + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (nextPage.getBoardNum() != 0){
            model.addAttribute("nextPageNotFound", false);
            nextPage.setTypeNum(typeNum);
            model.addAttribute("nextPage", nextPage);
        } else {
            nextPage.setTypeNum(typeNum);
            model.addAttribute("nextPage", nextPage);
            model.addAttribute("nextPageNotFound", true);
        }

        return "content/common/qna_detail";
    }


    // 문의사항 게시글 삭제
//    @GetMapping("/deleteQna")
//    public String deleteQna(@RequestParam(name = "boardNum") BoardVO boardVO, int boardNum){
//        boardService.deleteQna(boardNum);
//        if (boardVO.getTypeNum() == 3) {
//            return "redirect:/board/qnaList?typeNum=3";
//        } else {
//            return "redirect:/error";
//        }
//    }

    // 문의사항 게시글 삭제
    @GetMapping("/deleteQna")
    public String deleteQna(@RequestParam(name = "boardNum") int boardNum, BoardVO boardVO) {
        boardService.deleteQna(boardNum);
        if (boardVO.getTypeNum() == 3) {
            return "redirect:/board/qnaList?typeNum=3";
        } else {
            return "redirect:/error";
        }
    }

    // 문의사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateQna")
    public String updateQna(@RequestParam(name = "boardNum", required=false) int boardNum,
                            @RequestParam(name = "typeNum", required = false) int typeNum,Model model){
        BoardVO boardVO = new BoardVO();
        boardVO.setTypeNum(typeNum);
        boardVO.setBoardNum(boardNum);
        model.addAttribute("qna", boardService.selectNoticeDetail(boardVO));
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
