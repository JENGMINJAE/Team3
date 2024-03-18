
function inputDirectSc(testNum, classNum){
    
    const rowStuCnt  = parseInt(document.querySelector('#rowStuCnt').value);
    const colSubCnt  = parseInt(document.querySelector('#colSubCnt').value);
    
    
    
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
    
        const directTbody  = document.querySelector('#directTbody');      
        directTbody.innerHTML='';
            
        let str='';
        
                str+= `<table class="table table-striped table-hover text-center align-middle">
                            <colgroup>
                                <col width="40%">
                                <col width="*">
                                <col width="*">
                                <col width="*">
                            </colgroup>
                            <thead>
                                <tr>
                                    <td> 이름</td> `;
        
                        data.subsList.forEach(function(subN,t){
                            // str+= ` <td>
                            //             <input type="text" value="${subN.subName}">
                            //             <input type="hidden" value=${subN.subTestNum} name="subTestNum">
                            //             <input type="hidden" value=${subN.testNum} name="testNum">
                            //         </td>`;    
                            str+= ` <td>
                                        ${subN.subName}
                                    </td>`;    
                            
                        })
                         str+= `</tr>
                            </thead>  
                                <tbody id="score-tbody">`;
                                         
                                data.stuCnt.forEach(function(stuSub,y){
                                    str+=`<tr>
                                    <td>
                                    ${stuSub.memberName} <span class="score-member-id">[${stuSub.memberId}]</span>
                                    
                                    </td>`;
                                        
                                        for(let k =0; k<colSubCnt; ++k){
                                            str+= ` <td>
                                            
                                                        <input class="form-control" type="text" class="score-input">
                                                        <input type="hidden" value=${stuSub.memberId} name="memberId">
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
    
    
    
    
    
    
    //document.querySelector("#subform").submit();
    
    
    
    
function goInsertSubNtest(){
    
        //alert(55555555);
        //document.querySelector("#subform").submit();

        //전체 데이터를 가져가기 위한 통
        const scoreList = [];

        //-----------------데이터 가겨오기----------------//
        //1. 모든 데이터가 들어있는 tbody 태그를 선택
        const score_tbody = document.querySelector('#score-tbody');
        //데이터를 가져와야 하는 학생수
        const stuLen = score_tbody.children.length;

        //학생의 수만큼 데이터 가져오기를 반복
        for(let i = 0 ; i < stuLen ; i++){
              // const each = {
                //     score: ?,
                //     testsNum : ?,
                //     memberId:?,
                //     subTestNum:?
                // }

                scoreList.push(each);
        }


      
        

        // fetch('/test/insertSubNtest', { //요청경로
        //     method: 'POST',
        //     cache: 'no-cache',
        //     headers: {
        //         'Content-Type': 'application/json; charset=UTF-8'
        //     },
        //     //컨트롤러로 전달할 데이터
        //     body: JSON.stringify({
        //        // 데이터명 : 데이터값

        //     })
        // })
        // .then((response) => {
        //     return response.json(); //나머지 경우에 사용
        // })
        // //fetch 통신 후 실행 영역
        // .then((data) => {//data -> controller에서 리턴되는 데이터!
            
        // })
        // //fetch 통신 실패 시 실행 영역
        // .catch(err=>{
        //     alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        //     console.log(err);
        // });



    
}
    