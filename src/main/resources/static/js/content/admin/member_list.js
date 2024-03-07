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
        // return response.text(); //나머지 경우에 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        // 모달 상세 정보 하단
        const modal_tbody = document.querySelector('.tbody-tag');

        modal_tbody.innerHTML = '';
        modal_tbody.replaceChildren();


        let str = '';
        str +=  `
                    <tr>
                        <td class="table-active">이름(ID)</td>
                        <td id="modal-memberName">
                            <input class="form-control" type="text" name="memberName" value="${data.memberName}(${data.memberId})"> 
                        </td>
                        <td class="table-active">나이</td>
                        <td id="modal-memberAge">${data.memberAge}</td>
                    </tr>
                    <tr>
                        <td class="table-active">성별</td>
                        <td colspan="3" id="modal-memberName">
                    <div class="row">`;

                    if(data.memberGender == '남'){
                        str += `
                                <div class="offset-2 col-4">
                                    <input class="form-check-input" type="radio" name="memberGender" disabled checked value="남">남자
                                </div>
                                <div class="col-4">
                                    <input class="form-check-input" type="radio" name="memberGender" disabled value="여">여자
                                </div>
                                `;
                    }
                    else{
                        str += ` <div class="offset-2 col-4">
                        <input class="form-check-input" type="radio" name="memberGender" disabled value="남">남자
                    </div>
                    <div class="col-4">
                        <input class="form-check-input" type="radio" name="memberGender" disabled checked value="여">여자
                    </div>
                                `;
                    }
                        
                        
        str +=     `
                    </div>
                    </td>
                </tr>
                    <tr>
                        <td class="table-active">연락처</td>
                        <td id="modal-memberTel">
                        <input class="form-control" type="text" name="memberTel" value="${data.memberTel}"></td>
                        <td class="table-active">메일</td>
                        <td id="modal-memberRoll">
                        <input class="form-control" type="text" name="memberEmail" value="${data.memberEmail}"></td>
                    </tr>
                    <tr>
                        <td class="table-active">주소</td>
                        <td><input type="text" class="form-control" id="postCode" name="postCode" readonly></td>
                        <td colspan="2" id="modal-memberAddr"><input type="text" class="form-control" id="roadAddr" value="${data.memberAddr}" name="memberAddr" readonly>
                        <input type="button" class="btn btn-primary" value="주소검색" onclick="addrModal()">
                        </td>
                    </tr>
                    <tr>
                        <td class="table-active">상세 주소</td>
                        <td colspan="3"> 
                        <input type="text" class="form-control" value="${data.addrDetail}" name="addrDetail">
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

function addrModal(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#postCode').value = data.zonecode;
            document.querySelector('#roadAddr').value = data.roadAddress;
          }
        }).open();
}

function updateMemberInfo(){
    
}