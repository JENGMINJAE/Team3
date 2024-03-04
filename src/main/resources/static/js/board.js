// 공지사항 게시글 작성(관리자만)
function goWrite(){
    // 1. 관리자가 아닌 경우 공지사항 리스트로 이동
    // const idCheck = document.querySelector('#memberId')
    // if(idCheck.value != 3){
    //     alert('관리자가 아닙니다. \n 작성 권한이 없습니다.')
    //     location.href = '/board/noticeList';
    // }
    // 2. 관리자인 경우 작성 페이지로 이동 (memberRoll == 3)
    location.href = '/board/writeForm';
}

// function posting(){

    
// }

// 공지사항 검색
function boardSearch(){
    
}