// ##########################(과목별 시험)입력생성버튼 클릭시, 학생정보, 과목별 테이블 그리기 ##########################

function inputDirectSc(testNum, classNum){
    
    const rowStuCnt  = parseInt(document.querySelector('#rowStuCnt').value);
    const colSubCnt  = parseInt(document.querySelector('#colSubCnt').innerHTML);
    
    
            // ------------------- 첫번째 방식 ---------------//
            fetch('/test/subSelectStu', { //요청경로
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                //컨트롤러로 전달할 데이터
                body: new URLSearchParams({
                // 데이터명 : 데이터값
            
                'testNum':testNum,
                'classNum' :classNum
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
                console.log(data);
            
                const warnBtn    = document.querySelector('.warnBtn');  
                const directTbody  = document.querySelector('#directTbody');      
                warnBtn.innerHTML='';
                directTbody.innerHTML='';
                    
                let str='';
                
                        str+= `<table class="table table-hover text-center align-middle">
                                    <colgroup>
                                        <col width="20%">
                                        <col width="*">
                                        <col width="*">
                                        <col width="*">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <td class="trTitle"> <strong>이름</strong> </td> `;
                
                                data.subsList.forEach(function(subN,t){
                                    str+= ` <td class="trTitle">
                                                    <strong>${subN.subName}</strong>
                                                <input type="hidden" class="subNum" value="${subN.subTestNum}">
                                            </td>`;    
                                    
                                })
                                str+= `</tr>
                                    </thead>  
                                        <tbody id="score-tbody">`;
                                                
                                        data.stuCnt.forEach(function(stuSub,y){
                                            str+=`<tr>
                                            <td>                                               
                                            ${stuSub.memberName} [<span class="score-member-id">${stuSub.memberId}</span>]                                           
                                            </td>`;
                                                
                                                for(let k =0; k<colSubCnt; ++k){
                                                    str+= ` <td>                                               
                                                                <input class="form-control" type="text" class="score-input">                                                               
                                                            </td>`;
                                                }
                                            str+= `</tr>`
                                        });                                                                              
                                    str+=  `</tbody>
                                </table>`;
                
                                    directTbody.insertAdjacentHTML('afterbegin',str);                                                                                       
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err=>{
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
}
    
    
    
// ##########################(과목별 시험) 입력된 성적 저장버튼 ##########################    
function goInsertSubNtest(){
    

        //전체 데이터를 가져가기 위한 통
        const scoreList = [];

        //-----------------데이터 가겨오기----------------//

        //1. 모든 데이터가 들어있는 tbody 태그를 선택
        const score_tbody = document.querySelector('#score-tbody');
        //데이터를 가져와야 하는 학생수
        const stuLen = score_tbody.children.length;
        console.log(stuLen);

        //tr 태그
        const trs = score_tbody.children;

        //과목정보
        let title_cols = score_tbody.closest('table').querySelectorAll('thead > tr > td:not(:first-child)');

        //과목을 배열로 저장
        const title_arr = [];
        for(const title_col of title_cols){
            title_arr.push(title_col.querySelector('input').value);
        }

        //학생의 수만큼 데이터 가져오기를 반복
        for(const tr_tag of trs){
            
            //학생의 과목 수만큼 만큼(열 갯수만큼)
            for(let i = 0 ; i < title_arr.length ; i++){
                    const each = {
                        score: tr_tag.children[i + 1].querySelector('input').value,
                        testNum : document.querySelector('#hidden_test_num').value,
                        memberId:tr_tag.querySelector('.score-member-id').textContent,
                        subTestNum:title_arr[i]
                    }
                scoreList.push(each);
            }
        }
        console.log(scoreList);
        
        goInsert(scoreList);

}
    

function goInsert(scoreList){

        
    
    scoreList.forEach(function(scoreTr){
        console.log(scoreTr);
    
        // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
                fetch('/test/insertSubNtest', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: JSON.stringify({
                    // 데이터명 : 데이터값
                    'score': scoreTr.score ,
                    'testNum': scoreTr.testNum,
                    'memberId': scoreTr.memberId,
                    'subTestNum': scoreTr.subTestNum
                    })
                })
                .then((response) => {
                    return response.text();
                    //return response.json(); //나머지 경우에 사용
                })
                //fetch 통신 후 실행 영역
                .then((data) => {//data -> controller에서 리턴되는 데이터!
                    
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });


    })
    // console.log(333333);
    // console.log(tr);

      


}






 


