function btnControl(rBtn){
    const labels = document.querySelectorAll('.aaa');
    for(const label of labels){
        if(label.previousElementSibling.checked){
            
        }
        else{
            label.className = '';
            label.className = 'aaa btn btn-outline-secondary';
        }
    }

    const label_tag = rBtn.nextElementSibling;
    switch (rBtn.value) {
        case '1':
            label_tag.classList.add('btn-outline-success');
            break;
        case '2':
            label_tag.classList.add('btn-outline-danger');
            break;
        case '3':
            label_tag.classList.add('btn-outline-warning');
            break;
        case '4':
            label_tag.classList.add('btn-outline-secondary');
            break;
        case '5':
            label_tag.classList.add('btn-outline-info');
            break;
    }
}
const class_select = document.querySelector("#class_select");
function change(){
    const class_num = class_select.options[class_select.selectedIndex].value;
    fetch('/learn/changeStuOption', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            'classNum' : class_num
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        const stu_select = document.querySelector('#stu_select');
        let str = ``;
        stu_select.innerHTML = ``;

        data.studentList.map(function(student, index_1){
            str+=`
                <tr>
                    <td>
                        <input type="hidden" name="memberId" value="${student.memberId}">
                        ${student.memberName}
                    </td>
                `;


                data.atdList.map(function(atd, index_2){
                    str+=`
                    <td>
                        <input type="radio" onclick="btnControl(this)" class="btn-check" name="atdtNum_${index_1}" id="${index_1+'_'+index_2}" autocomplete="off" value="${atd.atdtNum}">
                        <label class="aaa btn btn-outline-secondary" for="${index_1+'_'+index_2}">${atd.atdtName}</label>
                    </td>
                    `;
                })
            str+=`</tr>`;
        });
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
change();




