function searchAddress(){
    new daum.Postcode({
      oncomplete: function(data) {
          document.querySelector('#postCode').value = data.zonecode;
          document.querySelector('#roadAddr').value = data.roadAddress;
        }
      }).open();
  }

function join(){
    //0. submit 전에 유효성 검사 
    // -1) ID를 입력했는지 확인
    const idInput = document.querySelector('#memberId');
    if(idInput.value == ''){
      alert('id는 필수 입력 사항입니다.');  
      return ;
    }
    // -2) id 입력 문자의 길이가 20을 초과하는지 검사
    if(idInput.value.lenght > 20){
      alert('id는 20글자 내로 작성하세요.');  
      return ;
    }
    // -3) pw 두 개 입력
    const pwInputs = document.querySelectorAll('input[type="password"]');
    if(pwInputs[0].value != pwInputs[1].value){
        alert('입력한 두 비밀번호가 다릅니다. \n 비밀번호를 다시 입력해주세요');
        return ;
    }
}
    