const teacherCode = document.querySelector('#updateTeacherNum').value;

console.log(teacherCode);

if(teacherCode != 0){
    teacherInfo(teacherCode, className);
}
function teacherInfo(teacherCode, className){

    fetch('/admin/selectTeacher', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           teacherNum : teacherCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);

        const make_spot = document.querySelector('.teacher-list-div');

        make_spot.innerHTML = '';

        let str = '';
        str += `
            <div class="row">
                <div class="col-2 text-center">강사명</div>
                <div class="col-10 d-grid">
                    <input class="form-control" type="text" value="${data.teacherVO.memberName}">
                </div>
            </div>
            <div class="row align-middle">
                <div class="col-2 text-center">반</div>
                <div class="col-10 d-grid">
                    <input class="form-control" type="text" value="${data.className}" readonly>
                </div>

            </div>
            <div class="row mt-2">
                <div class="col">
                    <table class="table table-primary">
                        <colgroup>
                            <col width="20%">
                            <col width="30%">
                            <col width="20%">
                            <col width="30%">
                        </colgroup>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>인원수</td>
                                <td>강의 상태</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>20 / 40</td>
                                <td>수업 중</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="row">
                <div class="col-2 text-center">재직상태</div>
                <div class="col-10">
                    <div class="row">
                        <form action="/admin/changeAttendance" method="post" id="insert-atd-form">
                        <input type="hidden" name="teacherNum" th:value="">
                            <div class="col">
                                <input class="form-check-input" type="radio" name="workNum" value="1"> 재직 
                            </div>
                            <div class="col">
                                <input class="form-check-input" type="radio" name="workNum" value="2"> 퇴직 
                            </div>
                        </form>
                    </div>
                </div>
                
                
            </div>

            <div class="row"> 
                <div class="col-8"></div>
                <div class="col-4 d-grid"> 
                    <input type="button" class="btn btn-primary" value="변경">
                </div>
            </div>
        `;

        make_spot.insertAdjacentHTML("afterbegin", str);
        document.querySelector('#insert-atd-form').submit;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
    
    
}