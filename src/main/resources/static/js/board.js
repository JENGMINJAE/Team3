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

// 공지사항 게시글 작성 시 필수입력 안내창
function noticeReg() {
    // 제목 빈칸 시
    const boardTitle = document.querySelector('#boardTitle');
    if (boardTitle.value == '') {
        alert('공지사항 제목을 입력하세요.');
        return false;
    }

    // 열람대상 빈칸 시
    const typeNum = document.querySelector('input[name="typeNum"]:checked');
    if (!typeNum) {
        alert('공지사항 열람대상을 선택하세요.');
        return false;
    }

    // 내용 빈칸 시
    const boardContent = document.querySelector('#boardContent');
    if (boardContent.value == '') {
        alert('공지사항 내용을 입력하세요.');
        return false;
    }

    // 유효성 검사 모두 만족 시 true
    return true;
}

// 게시글 등록 버튼 클릭 시 유효성 검사 후 제출
function submitNotice() {
    if (noticeReg()) {
        const post = document.querySelector('form');
        post.submit();
    }
}


////////////////////////////////////////////////////////////////////////////////////////
//게시글 수정 시 첨부파일 삭제
// function goDeleteImg(imgNum, boardNum) {
//     if (confirm('첨부파일을 삭제하시겠습니까?')) {
//         document.getElementById("deletedImgNum").value = imgNum;
//         document.getElementById("updateForm").submit();
//     }
// }

// 수정 완료 버튼 클릭 시
function submitForm() {
    // 수정된 내용을 서버로 전송
    document.getElementById("updateForm").submit();
}

//////////////////////////////////////////////////////////////////////////////////////

//공지사항 게시글 수정 시 첨부파일 삭제 - 비동기
function goDeleteImg(button, imgNum, boardNum){
    const imgNumber = button.getAttribute('data-img-num');
    const boardNumer = button.getAttribute('data-board-num');

        fetch('/board/deleteImgFile', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               // 데이터명 : 데이터값
               'imgNum' : imgNum,
               'boardNum' : boardNum
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
    
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            //수정된 화면 갱신
            alert('첨부파일이 삭제되었습니다.');

            // const deleteImgFile = document.querySelector('');

        })
        
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }

////////////////////////////////////////////////////////////////////////////////

// 공지사항 게시글 수정 - 첨부파일 첨부 - 비동기
// document.getElementById('upload-btn').addEventListener('click', function() {
//     const input = document.getElementById('file-input'); // 수정: 'file-upload-input' -> 'file-input'
//     const files = input.files;
//     const formData = new FormData();

//     for (let i = 0; i < files.length; i++) {
//         formData.append('subImgs', files[i]);
//     }

//     fetch('/insertImgFile', { // 수정: '/your-server-endpoint' -> '/insertImgFile'
//         method: 'POST',
//         body: formData,
//     })
//     .then(response => response.json())
//     .then(data => {
//         console.log(data); 
//         alert('파일이 성공적으로 업로드되었습니다.');
//     })
//     .catch(error => {
//         console.error('Error:', error);
//         alert('파일 업로드 중 오류가 발생했습니다.');
//     });
// });










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

//문의사항 게시글 작성 시 필수입력 안내창
function qnaReg() {
    // 제목 빈칸 시
    const boardTitle = document.querySelector('#boardTitle');
    if (boardTitle.value == '') {
        alert('문의사항 제목을 입력하세요.');
        return false;
    }

    // 열람대상 빈칸 시
    const typeNum = document.querySelector('input[name="typeNum"]:checked');
    if (!typeNum) {
        alert('문의사항 열람대상을 선택하세요.');
        return false;
    }

    // 내용 빈칸 시
    const boardContent = document.querySelector('#boardContent');
    if (boardContent.value == '') {
        alert('문의사항 내용을 입력하세요.');
        return false;
    }

    // 유효성 검사 모두 만족 시 true
    return true;
}

// 게시글 등록 버튼 클릭 시 유효성 검사 후 제출
function submitQna() {
    if (qnaReg()) {
        const post = document.querySelector('form');
        post.submit();
    }
}


//문의사항 댓글 유효성 검사
function goReplyReg(){
    // 댓글 내용 빈칸 시
    const replyContent = document.querySelector('#replyContent');
    if (replyContent.value == '') {
        alert('댓글 내용을 입력하세요.');
        return false;
    }

    // 유효성 검사 모두 만족 시 true
    return true;
}


//문의사항 댓글 삭제
function goDeleteReply(replyNum, boardNum){
    if(confirm('댓글을 삭제하시겠습니까?')){
        location.href=`/reply/deleteReply?replyNum=${replyNum}&boardNum=${boardNum}`;
    }
}


//문의사항 댓글 변경
function goUpdateReply(selectedTd ,boardNum, replyNum, replyContent){
    const Td = selectedTd.parentElement.parentElement.previousElementSibling.lastElementChild;

    Td.innerText = '';
    Td.innerHTML = `<input class="form-control" type="text" name="replyContent" id="changeInput" value="${replyContent}"/>
                    <input id="rcb" class="btn btn-primary" type="button" value="진짜수정"/>`;
    const rcb = document.querySelector("#rcb")
    rcb.addEventListener('click',function(){
        const newReplyContent = changeInput.value;
        fetch('/reply/updateReply', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               // 데이터명 : 데이터값
               'boardNum' : boardNum,
               'replyNum' : replyNum,
               'replyContent' : newReplyContent //수정된 댓글 내용
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
    
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            //수정된 댓글 화면 갱신
            alert('댓글이 변경되었습니다.');
            Td.textContent = data;
    
        })
        
    
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    })
   
    // const newReplyContent = prompt('댓글 내용을 입력하세요:', replyContent);
    // if (!newReplyContent) {
    //     return; // 사용자가 입력을 취소하거나 아무것도 입력하지 않은 경우
    // }
    
}


