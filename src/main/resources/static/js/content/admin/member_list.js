function modalDetail(){
    const member_detail_modal = new bootstrap.Modal('#member-detail-modal');

    member_detail_modal.show();
    console.log(data);
    fetch('/admin/memberDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
        //    memberId : memberId
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        // 모달 상세 정보 하단
        // const modal_tbody = document.querySelector('#modal-tbody');

        // modal_tbody.innerHTML = '';
        // modal_tbody.replaceChildren();

        
        // let str = '';
        // data.buyDetailList.forEach(function(detail, i){
        //     str +=  `<tr>
        //                 <td>${data.buyDetailList.length - i}</td>
        //                 <td> <img width="20%" src="/upload/${detail.itemVO.imgList[0].attachedFileName}">
        //                 ${detail.itemVO.itemName}</td>
        //                 <td>${detail.buyCnt}</td>
        //                 <td>${detail.totalPrice}</td>
        //             </tr>`
        // });


        // modal_tbody.insertAdjacentHTML('afterbegin', str);


        // member_detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}