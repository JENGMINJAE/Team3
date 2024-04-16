//유효성 검사 1. 아이디 길이 제한
function idMax(){
  const idDiv = document.querySelector('#checkID');
  const memberId = document.querySelector('#checkID').value;
  if(memberId.length > 20){
    alert('아이디를 20자 이내로 입력해주세요.')
    idDiv.value = '';
  }
}

//유효성 검사 2. 비밀번호 확인
function pwCheck(){
  const pwDiv = document.querySelector('#chkPw');
  const aPw = document.querySelector("#newPw").value;
  const bPw = document.querySelector("#chkPw").value;
  if(aPw != bPw){
    alert("두 비밀번호가 일치하지 않습니다. 다시 입력해 주십시오.")
    pwDiv.value = '';
  }
}

//유효성 검사 3. 전화번호 길이 제한
function phoneCheck1(){
  const phone1 = document.querySelector('#phone');
  

}


//아이디 중복 확인
function idCheck(){
  const memberId = document.querySelector('#checkID').value;

  fetch('/member/idCheckFetch', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
      // 데이터명 : 데이터값
        'memberId' : memberId
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
  .then((result) => {//data -> controller에서 리턴되는 데이터!
    if(result == 0){
      alert('사용 가능한 아이디입니다.')
    }
    else{
      alert('중복되는 아이디입니다. \n 다른 아이디를 입력해주세요.')
    }
  })
  //fetch 통신 실패 시 실행 영역
  .catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
  });
}

// 주소 검색
function searchAddress(){
    new daum.Postcode({
      oncomplete: function(data) {
          document.querySelector('#postCode').value = data.zonecode;
          document.querySelector('#roadAddr').value = data.roadAddress;
        }
    }).open();
  }

  // 회원 가입
function join(){
    //0. submit 전에 유효성 검사 
    //1. ID 입력했는지 확인
    const memberId = document.querySelector('#checkID');
    if(memberId.value == ''){
      alert('아이디는 필수 입력 사항입니다. \n 아이디를 입력하세요.');  
      return false;
    }

    //2. id 입력 문자의 길이가 20을 초과하는지 검사
    // if(memberId.value.length > 20){
    //   alert('아이디는 20글자 내로 작성하세요.');  
    //   return false;
    // }
    
    //3. pw 두 개 입력
    // const aPw = document.querySelector("#newPw");
    // const bPw = document.querySelector("#chkPw");
    // if(aPw.value != bPw.value){
    //   alert("두 비밀번호가 일치하지 않습니다. 다시 입력해 주십시오.");
    //   return false;
    // }

    //모든 유효성 검사를 통과한 경우 true
    return true;
}

//유효성 검사 통과 후 회원가입 완료 처리
function submitJoin() {
  if(join()){
    const post = document.querySelector('form');
    post.submit();
    alert('회원가입이 완료되었습니다.');
  }
}
    