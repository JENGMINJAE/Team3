//////////////////////////////////////[공지사항 관련]////////////////////////////////////////

// 공지사항 게시글 작성(*관리자만)
function goWrite(){
    // 1. 관리자가 아닌 경우 공지사항 리스트로 이동
    // const idCheck = document.querySelector('#memberId')
    // if(idCheck.value != 3){
    //     alert('관리자가 아닙니다. \n 작성 권한이 없습니다.')
    //     location.href = '/board/noticeList';
    // }
    // 2. 관리자인 경우 작성 페이지로 이동 (memberRoll == 3)
    location.href = '/board/noticeWriteForm';
}

//공지사항 게시글 수정(*관리자만)
function goUpdateNotice(boardNum){
    if(confirm('공지사항을 수정하시겠습니까?')){
        location.href=`/board/updateNotice?boardNum=${boardNum}`;
    }
    
}


// 공지사항 게시글 삭제(*관리자만)
function goDeleteNotice(boardNum){
    if(confirm('공지사항을 삭제하시겠습니까?')){
        location.href=`/board/deleteNotice?boardNum=${boardNum}`;
    }
}


//////////////////////////////////////[문의사항 관련]////////////////////////////////////////

// 문의사항 게시글 작성
function goWriteQna(){
    location.href = '/board/qnaWriteForm';
}

//문의사항 게시글 수정
function goUpdateQna(boardNum){
    if(confirm('게시글을 수정하시겠습니까?')){
        location.href=`/board/updateQna?boardNum=${boardNum}`;
    }
    
}

// 문의사항 게시글 삭제
function goDeleteQna(boardNum){
    if(confirm('게시글을 삭제하시겠습니까?')){
        location.href=`/board/deleteQna?boardNum=${boardNum}`;
    }
}
