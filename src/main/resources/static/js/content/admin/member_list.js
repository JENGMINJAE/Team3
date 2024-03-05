function memberDetail(memberId){
    const member_detail_modal = new bootstrap.Modal('#member-detail-modal');

    // member_detail_modal.show();

    fetch('/admin/memberDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           memberId : memberId
        })
    })
    .then((response) => {
        return response.text(); //나머지 경우에 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        // 모달 상세 정보 하단
        const modal_tbody = document.querySelector('.tbody-tag');

        modal_tbody.innerHTML = '';
        modal_tbody.replaceChildren();


        // if(data.itemDetail.itemStatus == 1){
        //     str += ` <div class="col">
        //                 <input class="form-check-input" type="radio" name="itemStatus" value="1" checked> 준비중 
        //             </div>
        //             `;
        // }
        // else{
        //     str += ` <div class="col">
        //                 <input class="form-check-input" type="radio" name="itemStatus" value="1"> 준비중 
        //             </div>
        //             `;
        // }
        let str = '';
        str +=  `
                    <tr>
                        <td class="table-active">이름(학생번호)</td>
                        <td id="modal-memberName">${memberId}(1)</td>
                        <td class="table-active">나이</td>
                        <td id="modal-memberAge">18</td>
                    </tr>
                    <tr>
                        <td class="table-active">연락처</td>
                        <td id="modal-memberTel">010-3333-2222</td>
                        <td class="table-active">소속</td>
                        <td id="modal-memberRoll">
                            <input type="radio" class="form-check-input" name="memberRoll" value="1" id="memberRoll-select"> 학생
                            <input type="radio" class="form-check-input" name="memberRoll" value="2"> 강사
                            <input type="radio" class="form-check-input" name="memberRoll" value="3"> 관리

                        </td>
                    </tr>
                    <tr>
                        <td class="table-active">주소</td>
                        <td colspan="3" id="modal-memberAddr">울산시 남구 삼산동 654-1 업스퀘어</td>

                    </tr>
                    <tr>
                        <td class="table-active">반이름</td>
                        <td id="modal-buyPrice">자바반</td>
                        <td class="table-active">등록일</td>
                        <td id="modal-buyDate">2024-03-04</td>
                    </tr>
                    <tr>
                        <td class="table-active">상태</td>
                        <td colspan="3" id="modal-buyPrice">
                            <input type="radio" name="memberRoll" value="1">재직
                            <input type="radio" name="memberRoll" value="2">퇴직
                        </td>
                    </tr>
                `;


        modal_tbody.insertAdjacentHTML('afterbegin', str);


        member_detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}