


const test_detail_modal = new bootstrap.Modal('#testJoin-form');


// //////////////////////////////////////////////// 평가명 추가 버튼 클릭시 모달 창 오픈!!!!!////////////////////////////////////


function happyBtn(){
    
        

    
        const classNum = document.querySelector('#classNum').value; 
        const testAddBtn = document.querySelector('.testAddBtn');
        
        testAddBtn.innerHTML=``; 
        
                let str= ` <input type="button" value="시험추가" id="btn insertTestNameBtn " onclick="insertScore()">`        
        
                            testAddBtn.insertAdjacentHTML('afterbegin',str);

        // ---------------------------------------- --- 첫번째 방식 -----------------------------------//
        fetch('/test/selectTest', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                'classNum' : classNum

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
            

            const sel = document.querySelector('#sel');
            
            sel.innerHTML=``; 
            let std=``;
        
            data.forEach(function(testOne, idx){
                std+=`<option value=${testOne.testNum}>${testOne.testName}</option>`;     
                 });
        
                sel.insertAdjacentHTML('afterbegin',std); 
                test_detail_modal.show();

            
            

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
        
        
}








// ////////////////////////////////////////////////-모달내의 추가 버튼 클릭시 평가명 insert되고 목록조회!!!!////////////////////////////////////////////////

  function insertScore(){
    
                const testName =document.querySelector('#testName').value;
                const classNum = document.querySelector('#classNum').value;
                const testMaxScore = document. querySelector('#testMaxScore').value;
                const testDate= document.querySelector('#testDate').value;
                
                

            fetch('/test/insertTest', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    
                    'testName': testName,
                        'testMaxScore': testMaxScore,
                        'testDate': testDate,
                        'classNum' : classNum

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
                
                    
                    InfoTestInput();
                    happyBtn();
                    

                

                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });
}










// ////////////////////////////////////////////////모달 내, 평가명등록 버튼 클릭시 input 태그 리셋 ////////////////////////////////////////////////

function InfoTestInput(){

            const values = ['#testName', '#testMaxScore', '#testDate']

            for(let i = 0; i<values.length; i++){
                let input = document.querySelector(values[i]);
                input.value=input.defaultValue;
            }
}



function updateScorePageTwo(){
    const values = ['#testName', '#testMaxScore', '#testDate']

            for(let i = 0; i<values.length; i++){
                let input = document.querySelector(values[i]);
                input.value=input.defaultValue;
            }
    const classNum = document.querySelector('#classNum').value;
    location.href='/test/scoreTeacher?classNum='+classNum;      

}




 // -////////////////////////////////////////////////-모달 창닫기 버튼 눌리면 성적목록 리로딩되어 평가명이 업데이트됨 ////////////////////////////////////////////////

 function updateScorePage(){
    const classNum = document.querySelector('#classNum').value;
 location.href='/test/scoreTeacher?classNum='+classNum;
}




// -//////////////////////////////////////////////// 조회 클릭시 학생성적 조회////////////////////////////////////////////////////




function goSelectScore(testNum){

    
    
   

    // ------------------- 첫번째 방식 ---------------//
    fetch('/test/selectScoreList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'testNum':testNum
        
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
       
       
        
      
        if(data.length == 0){
            alert('성적이 비워져있습니다.');
           
         }
       

        const trRowTwo = document.querySelector('.tr-rowTwo');
        trRowTwo.innerHTML='';
        let str=``;
        str+= `<col width="15%">
               <col width="35%">
               <col width="20%">
               <col width="30%">
                    <tr>
                        <th>학생번호</th>
                        <th>학생명</th>
                        <th>점수</th>
                        <th>시험명</th>                   
                    </tr>`                                           


        data.forEach(function(scoreStu, idx){
            str+=`<tr>
                    <td>${scoreStu.memberId}</td>
                    <td>${scoreStu.memberOneVO.memberName}</span></td>
                    <td>${scoreStu.score}</td>
                    <td>${scoreStu.testOneVo.testName}</td>
                <tr>`;     
             });


             trRowTwo.insertAdjacentHTML('afterbegin',str);
             str = '';
            
                                 
        
  

        


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });



}









// /////////////////////////////////////ScoreViewVO  성적통계 조회//////////////// 



function showTatalScore(classNum){
    location.href='/test/totalScoreShow?classNum='+classNum;
     alert(11111);



     

};


// ////////// 학생 점수 입력창 ////////////////

function goInputScore(testNum){
    location.href='/test/goInputScore?testNum='+testNum;
};



