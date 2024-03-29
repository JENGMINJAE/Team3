package com.green.Team3.board.service;

import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.ImgVO;
import com.green.Team3.board.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {


    @Autowired
    private SqlSessionTemplate sqlSession;

    //다음에 INSERT 할 BOARD_NUM 조회
    @Override
    public int selectNextNoticeCode() {
        return sqlSession.selectOne("board.selectNextNoticeCode");
    }

    //게시글 목록 조회 - 공지사항
    @Override
    public List<BoardVO> selectNoticeList(SearchVO searchVO) {
        List<BoardVO> list = sqlSession.selectList("board.selectNoticeList", searchVO);
        return list;
    }

    //게시글 목록 조회 - 문의사항
    @Override
    public List<BoardVO> selectQnaList(SearchVO searchVO) {
        List<BoardVO> list = sqlSession.selectList("board.selectQnaList", searchVO);
        return list;
    }

    //게시글 등록 - 공지사항(게시글 + 첨부파일)
    //트랜젝션
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertNotice(BoardVO boardVO) {
        sqlSession.insert("board.insertNotice", boardVO);
        if(!boardVO.getImgList().isEmpty()){
            sqlSession.insert("board.insertImgs", boardVO);
        };
    }

    //게시글 등록 - 문의사항
    @Override
    public void insertQna(BoardVO boardVO) {
        sqlSession.insert("board.insertQna", boardVO);
    }

    //게시글 상세 조회 - 공지사항
    @Override
    public BoardVO selectNoticeDetail(int boardNum) {
        BoardVO result = sqlSession.selectOne("board.selectNoticeDetail", boardNum);
        return result;
    }

    //게시글 상세 조회 - 문의사항
    @Override
    public BoardVO selectQnaDetail(int boardNum) {
        BoardVO result = sqlSession.selectOne("board.selectQnaDetail", boardNum);
        return result;
    }

    //게시글 조회수 증가
    @Override
    public void updateBoardCnt(int boardNum) {
        sqlSession.update("board.updateBoardCnt", boardNum);
    }

    //게시글 삭제 - 공지사항 (게시글 + 이미지 삭제)
    //트랜젝션
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNotice(BoardVO boardVO) {
        int imgCnt = sqlSession.selectOne("board.hasImg", boardVO);
        if(imgCnt > 0){
            sqlSession.delete("board.deleteImg", boardVO);
        }
        sqlSession.delete("board.deleteNotice", boardVO);
    }

    //게시글 삭제 - 문의사항
    @Override
    public void deleteQna(int boardNum) {
        sqlSession.delete("board.deleteQna", boardNum);
    }


    //공지사항 게시글 수정 시 첨부파일 이미지 삭제 *******************************
    @Override
    public void deleteImgFile(int imgNum) {
        sqlSession.delete("board.deleteImgFile", imgNum);
    }

    //공지사항 게시글 수정 시 첨부파일 이미지 첨부 *******************************
    @Override
    public void insertImgs(BoardVO boardVO) {
        sqlSession.insert("board.insertImgs", boardVO);
    }

    //게시글 첨부파일 유무 확인
    @Override
    public boolean hasImg(int boardNum) {
        return false;
    }



    //문의사항 게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("board.updateBoard", boardVO);
    }

    //공지사항 게시글 수정 - 첨부파일 수정까지 ************************************(구현중)
//    @Override
//    public void updateImgFile(BoardVO boardVO, int imgNum) {
//        sqlSession.update("board.updateImgFile", boardVO);
//    }

    //게시글 수 조회
    @Override
    public int selectNoticeCnt(SearchVO searchVO) {
        return sqlSession.selectOne("board.selectNoticeCnt", searchVO);
    }

    //게시글 상세 - 이전글 조회
    @Override
    public BoardVO prevPage(int boardNum) {
        return sqlSession.selectOne("board.prevPage", boardNum);
    }

    //게시글 상세 - 다음글 조회
    @Override
    public BoardVO nextPage(int boardNum) {
        return sqlSession.selectOne("board.nextPage", boardNum);
    }


}
