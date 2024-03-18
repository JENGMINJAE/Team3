


const test_detail_modal = new bootstrap.Modal('#testJoin-form');


// //////////////////////////////////////////////// 평가명 추가 버튼 클릭시 모달 창 오픈!!!!!////////////////////////////////////

let subTotal = document.querySelector('#subTotal').value;

//const classNum = document.querySelector('#classNum').value;



function happyBtn(classNum){
    
     

    // 시험추가 버튼 생성하기
    const testAddBtn = document.querySelector('.testAddBtn');        
    testAddBtn.innerHTML=''; 

            let str= ` <input type="button" value="시험추가" id="btn insertTestNameBtn " onclick="insertScore()">`        

                        testAddBtn.insertAdjacentHTML('afterbegin',str);



    // 과목 없음 / 과목 있음 선택시                            
    const classGoSub =document.querySelector('.classGoSub');
    const radios = document.querySelectorAll('input[type="radio"]');

radios.forEach(function(rad,i){
    rad.addEventListener('click', function(e){

            const current = e.currentTarget;
            const hiddenPerfect =document.querySelector('#hiddenPerfect');

        if(current.value == 2){
                console.log(77777);
                                   
                
                    // 만점입력하는 시험추가 버튼 사라지게 함
                    hiddenPerfect.innerHTML=``;
                     let sti= ``;
                    hiddenPerfect.insertAdjacentHTML('afterbegin',sti)

                    // 시험명, 날짜만 저장하는 시험추가 버튼 생성
                    testAddBtn.innerHTML=``;        
                     let sth= `
                                <input type="button" value="시험추" id="btn insertTestNameBtn " onclick="insertSubSc()">` 
                                testAddBtn.insertAdjacentHTML('afterbegin',sth);
     
                // 과목 입력 생성 
                 classGoSub.innerHTML=``;
                 let stj=` <div class="row">
                 <div class="col" style="font-size:15px; color: red; font-weight: 15px;">
                     * 시험추가 한 후, 과목을 추가해주세요.
                 </div>
             </div>

            <div class="row">
                <div class="col addSub">
                     
                    <div class="row">

                        <div class="col-6">

                             <div class="row">
                                 <div class="col">
                                 <label for="selSub">과목종류</label>
                                     <select class="form-select" size="3" aria-label="Size 3 select example"  id="selSub" style="font-size: 20px;" >
                                         <option></option>
                                         <option></option>
                                     </select>
                                 </div>
                             </div>
                             <div class="row">  
                                 <div class="col">
                                     <label for="subTotalMax">총만점</label>
                                     <input type="text" id="subTotalMax" style="width: 100px;">
                                 </div>       
                             </div> 


                        </div>

                     
                     
                     
                        <div class="col-6">
                        <div class="row">
                            <div class="col">
                                <input type="text" name="testNum" id="testNumForSub">
                            </div>
                        </div>
                            
                                <div class="row">
                                    <div class="col">
                                        <label for="subName">과목명</label>
                                        <input type="text" name="subName" id="subName" >
                                    </div>
                                </div>    
                                <div class="row">
                                    <div class="col">
                                        <label for="subMaxScore">과목만점</label>
                                        <input type="text" name="subMaxScore" id="subMaxScore">
                                    </div>       
                                </div>
                                <div class="row">
                                    <div class="col" id="addSubBtn">
                            
                                        <input type="button" id="subNameAdd" value="과목추가" onclick="goInsertSub()">
                                    </div>       
                                </div>
                            
                           
                         
                        </div>
                    </div>


                </div>
            </div>`
                 
                 
             classGoSub.insertAdjacentHTML('afterbegin',stj);
                        
        }

        else if(current.value == 1){
                    hiddenPerfect.innerHTML=``;
                    let stt = `<div class="row">
                                    <div class="col">
                                        시험만점   
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <input   class="testInfoInput"  type="text"  name="testMaxScore" id="testMaxScore" style="width: 100%;">
                                    </div>
                                </div>`
                    
                    hiddenPerfect.insertAdjacentHTML('afterbegin',stt)
                    
                    testAddBtn.innerHTML=``; 
        
                let str= `
                        
                            <input type="button" value="시험추가" id="btn insertTestNameBtn " onclick="insertScore()">`        
        
                            testAddBtn.insertAdjacentHTML('afterbegin',str);
                    

                            classGoSub.innerHTML=``;
                            let stj='';
                            classGoSub.insertAdjacentHTML('afterbegin',stj);                    
                    
        }

    })
           

})





// //// ///////////// 테스트 정보 조회  해서 모달로 가져오기 /////////////////////
        

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
                const testMaxScore = document.querySelector('#testMaxScore').value;
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
                    happyBtn(classNum);
                    

                

                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });
}

// ############################################ 과목있을때 메인테스트 저장 ################################


function insertSubSc(){

    //console.log(classNum);
    const testName =document.querySelector('#testName').value;
    const classNum = document.querySelector('#classNum').value;
    const testDate= document.querySelector('#testDate').value;

        // ------------------- 첫번째 방식 ---------------//
    fetch('/test/insertSubSc', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값

        'testName': testName,
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

        happyBtn(classNum);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}




///////##########################과목 입력시 메인테스트명 선택하여 value 값 꺼내기 ############################////////////

function changeTest(){

    const chTest  = document.querySelector('.chTest');
    const chTestValue = (chTest.options[chTest.selectedIndex].value);
    console.log(chTestValue);
 
    document.querySelector('#testNumForSub').value= chTestValue;
    chooseSub();

}   



// ############################################## 과목 총점 구하기 ####################################
 
function chooseSub(){  


    const testNumForSub = document.querySelector('#testNumForSub').value;

    // ------------------- 첫번째 방식 ---------------//
    fetch('/test/selectSubTest', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'testNum' : testNumForSub
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
        
        console.log('44444'+data.testNum2[0].testMaxScore);

        if(data.testNum2[0].testMaxScore ==0){
                 const selSub = document.querySelector('#selSub');
                    selSub.innerHTML=``; 
                    let std=``;
                
                    data.subNames.forEach(function(testSubOne, idx){
                        std+=`<option value=${testSubOne.subTestNum}>${testSubOne.subName}</option>`;     
                        });
                
                        selSub.insertAdjacentHTML('afterbegin',std); 
                    

        // 총점계산하여 넣기
            let subSum =0;
            const subCount = data.subNames.length;

            for(let i =0; i <subCount; i++){
                subSum+=parseInt(data.subNames[i].subMaxScore);
            }

            document.querySelector('#subTotalMax').value=subSum;

            const addSubBtn = document.querySelector('#addSubBtn');
            addSubBtn.innerHTML=``;
            let stt=``;
            stt=` <input type="button" id="subNameAdd" value="과목추가" onclick="goInsertSub()">`;  
        
            addSubBtn.insertAdjacentHTML('afterbegin',stt);
        } 



        else if (data.testNum2[0].testMaxScore !=0){

                console.log(33333333);

                const selSub = document.querySelector('#selSub');           
                selSub.innerHTML=``;
                let stf=``;
                selSub.insertAdjacentHTML('afterbegin',stf);

                document.querySelector('#subTotalMax').value ='';


            

                if(alert('과목설정이 없는 단일시험 입니다. 시험상세 정보를 확인하세요.')){
                        return; };
                

                const addSubBtn = document.querySelector('#addSubBtn');
                addSubBtn.innerHTML=``;
                let stt=``;
                stt=`<input type="button" id="subBtnAdd" value="과목">`;  

                addSubBtn.insertAdjacentHTML('afterbegin',stt);

                const subBtnAdd = document.querySelector('#subBtnAdd');
                subBtnAdd.addEventListener('click', function(event){
                    alert('과목설정이 없는 단일시험 입니다. 과목을 추가할 수 없습니다.')
                });


        }



    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

 
 }



function checkSubSum(subSum){

    console.log(44444444444444 + subSum)
}







// ############################## 과목 저장######################################


function goInsertSub(){

    
const subName = document.querySelector('#subName').value;
const subMaxScore = document.querySelector('#subMaxScore').value;
const testNumForSub = document.querySelector('#testNumForSub').value;

        // ------------------- 첫번째 방식 ---------------//
    fetch('/test/goInsertSub', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        
        'subName': subName,
        'subMaxScore': subMaxScore,
        'testNum': testNumForSub

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
        
       
       
        // 총점구하기
        chooseSub(); 
        document.querySelector('#subName').value="";
        document.querySelector('#subMaxScore').value="";

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



// function updateScorePageTwo(){
//     const values = ['#testName', '#testMaxScore', '#testDate']

//             for(let i = 0; i<values.length; i++){
//                 let input = document.querySelector(values[i]);
//                 input.value=input.defaultValue;
//             }
//     const classNum = document.querySelector('#classNum').value;
//     location.href='/test/scoreTeacher?classNum='+classNum;      

// }




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
        str+= `<col width="20%">
               <col width="30%">
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





// //////////////////////// 단일 시험 등록 또는 과목별 시험 등록 페이지 이동 ////////////////////////
function goSelectSub(testNum, classNum){
    console.log(classNum);
  
    location.href='/test/goTestN?testNum='+ testNum +'&classNum='+classNum;  
        
}




