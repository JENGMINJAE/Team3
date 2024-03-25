


const radios = document.getElementsByName('searchTest');
let memberId = document.querySelector('#mem').value;



function stuSearch(){

   
 
         radios.forEach(function(radi,i){
            // radi.addEventListener('click', function(e){

            //     const current = e.currentTarget;
            // })

            if(radi.checked){
                document.querySelector('#result').innerHTML= radi.value;


                    if(radi.value== 1){

                        classListSearch(memberId);
                    }


                    else if(radi.value== 2){
                        
                        testListSearch(memberId);

                    }

                    else if(radi.value== 3){
                        
                        subListSearch(memberId);

                    }

                    else if(radi.value== 4){
                        totalTest(memberId);

                    }





            }




          })
    }


function classListSearch(memberId){

        console.log(memberId);

        //------------------- 첫번째 방식 ---------------//
        fetch('/score/classListSearch', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'memberId': memberId
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
            
                 const stuTestList = document.querySelector('.stuTestList-div');
                 stuTestList.innerHTML=``;       
                 let stw= ``;
                
                
                stw+=  `<table class="table-blank text-center align-middle">
                            <colgroup>
                            <col width="10%">
                            <col width="20%">
                            <col width="10%">
                            <col width="30%">
                            <col width="20%">
                            <col width="10%">
                            </colgroup>
                            <tr>
                                <th>수강번호</th>
                                <th>수강명</th>
                                <th>수강상태</th>
                                <th>수강기간</th>
                                <th>시험명</th>
                                <th>성적확인</th>
                            </tr>`;

                    data.forEach(function(testStuOne, idx){     
                        stw+=   `<tr>
                                    <td>${testStuOne.classNum}</td>
                                    <td>${testStuOne.studentListVO[0].classOneVo.className}</td>                             
                                    <td>${testStuOne.studentListVO[0].classOneVo.classEnter}</td> 
                                    <td>${testStuOne.studentListVO[0].classOneVo.startDate}--${testStuOne.studentListVO[0].classOneVo.endDate}</td>
                                    <td>${testStuOne.testName}</td>
                                    <td>
                                    <input type="button" value="조회" id="btn" onclick="goStuScoreCheck(${testStuOne.testNum})">
                                    </td>
                                    
                                </tr>`;
                    })
                                
                     stw+= `</table>`;


                     stuTestList.insertAdjacentHTML('afterbegin',stw);     
                

              
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });


}


function testListSearch(memberId){

        //------------------- 첫번째 방식 ---------------//
        fetch('/score/testListSearch', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'memberId': memberId
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
            
                const stuTestList = document.querySelector('.stuTestList-div');
                stuTestList.innerHTML=``;       
                let stw= ``;
                
                
                stw+=  `<table class="table-blank text-center align-middle">
                            <colgroup>
                            <col width="10%">
                            <col width="25%">
                            <col width="10%">
                            <col width="20%">
                            <col width="25%">
                            <col width="10%">
                            </colgroup>
                            <tr>
                                <th>시험번호</th>
                                <th>시험명</th>
                                <th>시험만점</th>
                                <th>시험일자</th>
                                <th>과목수</th>
                                <th>성적확인</th>
                            </tr>`;

                    data.forEach(function(testDetailOne, idx){     
                        stw+=   `<tr>
                                    <td>${testDetailOne.testNum}</td>
                                    <td>${testDetailOne.testName}</td>                             
                                    <td>${testDetailOne.testPerfect}</td> 
                                    <td>${testDetailOne.testDate}</td>`;

                                if(testDetailOne.subCnt==0){
                                   stw+= `<td> --- </td>`;
                                }
                                
                                else if(testDetailOne.subCnt!=0){
                                    stw+=` <td> ${testDetailOne.subCnt} </td>` 
                                }
                                                                                                                                                                                                                     
                                stw+=`<td><input type="button" value="조회" id="btn" onclick="goStuScoreCheck(${testDetailOne.testNum})"></td>
                                    
                                </tr>`;
                    })
                                
                    stw+= `</table>`;


                    stuTestList.insertAdjacentHTML('afterbegin',stw);     
                

            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });


}

function subListSearch(memberId){


    //------------------- 첫번째 방식 ---------------//
    fetch('/score/subListSearch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'memberId': memberId
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
        
             const stuTestList = document.querySelector('.stuTestList-div');
             stuTestList.innerHTML=``;       
             let stw= ``;
            
            
            stw+=  `<table class="table-blank text-center align-middle">
                        <colgroup>
                        <col width="10%">
                        <col width="20%">
                        <col width="10%">
                        <col width="30%">
                        <col width="20%">
                        <col width="10%">
                        </colgroup>
                        <tr>
                            <th>과목번호</th>
                            <th>과목명</th>
                            <th>과목만점</th>
                            <th>시험명</th>
                            <th>시험일자</th>
                            <th>성적확인</th>
                        </tr>`;

                data.forEach(function(testSubOne, idx){     
                    stw+=   `<tr>
                                <td>${testSubOne.subTestNum}</td>
                                <td>${testSubOne.subName}</td>                             
                                <td>${testSubOne.subMaxScore}</td> 
                                <td>${testSubOne.testOneVO.testName}</td>
                                <td>${testSubOne.testOneVO.testDate}</td>
                                <td><input type="button" value="조회" id="btn" onclick="goStuScoreCheck(${testSubOne.testOneVO.testNum})"></td>
                            </tr>`;
                })
                            
                 stw+= `</table>`;


                 stuTestList.insertAdjacentHTML('afterbegin',stw);     
            

          
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });




}


function totalTest(memberId){


    //------------------- 첫번째 방식 ---------------//
    fetch('/score/totalListSearch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'memberId': memberId
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
        
             const stuTestList = document.querySelector('.stuTestList-div');
             stuTestList.innerHTML=``;       
             let stw= ``;
            
            
            stw+=  `<table class="table-blank text-center align-middle">
                        <colgroup>
                        <col width="10%">
                        <col width="20%">
                        <col width="10%">
                        <col width="30%">
                        <col width="20%">
                        <col width="10%">
                        </colgroup>
                        <tr>
                            <th>수강번호</th>
                            <th>수강명</th>
                            <th>수강상태</th>                           
                            <th>수강시작일자</th>
                            <th>수강종료일자</th>
                            <th>성적확인</th>
                        </tr>`;

                data.forEach(function(totalTestStu, idx){     
                    stw+=   `<tr>
                                <td>${totalTestStu.classNum}</td>
                                <td>${totalTestStu.classOneVo.className}</td>                             
                                <td>${totalTestStu.classOneVo.classEnter}</td> 
                                <td>${totalTestStu.classOneVo.startDate}</td>
                                <td>${totalTestStu.classOneVo.endDate}</td>
                                <td><input type="button" value="조회" id="btn" onclick="totalPrint()"></td>
                                
                            </tr>`;
                })
                            
                 stw+= `</table>`;


                 stuTestList.insertAdjacentHTML('afterbegin',stw);     
            

          
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });




}



function goStuScoreCheck(testNum){

  
     location.href='/score/goMyScore?testNum='+ testNum +'&memberId='+ memberId; 

}


function totalPrint(){
    alert(memberId);
    location.href='/score/totalStuPrint'; 
    //location.href='/score/goMyScore?memberId='+ memberId; 
}