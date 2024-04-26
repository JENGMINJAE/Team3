

const showTypes = () => {
  const typeName = document.querySelector('.name-input').value;
  const showTypes = document.querySelector('.show-types');

  fetch('/admin/setBoardType', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/json; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: JSON.stringify({
       // 데이터명 : 데이터값
       typeName : typeName
    })
  })
  .then((response) => {
      return response.text(); //나머지 경우에 사용
  })
  //fetch 통신 후 실행 영역
  .then((data) => {//data -> controller에서 리턴되는 데이터!

    let str =`
    <tr>
      <td class="num-td">${data}</td>
      <td>${typeName}</td>
      <td>
        <input type="button" class="btn btn-outline-success btn-button" value="수정" onclick="goRegFunc(this)">
        <input type="button" class="btn btn-outline-primary btn-button" value="삭제" onclick="delBoardType(this)">
      </td>
    </tr>
    `;

    showTypes.insertAdjacentHTML('beforeend', str);
    
  })
  //fetch 통신 실패 시 실행 영역
  .catch(err=>{
      alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
      console.log(err);
  });
}

const goRegFx = (selectedTag) => {
  const changeTypeName = selectedTag.parentElement.previousElementSibling;
  const selectPart = selectedTag.parentElement;
  
  changeTypeName.innerHTML = '';
  selectPart.innerHTML = '';

  let str = `
    <input type="text" name="typeName" class="txt-input" placeholder="수정할 분류명 입력">
  `;
  let str2 = `
    <td>
      <input type="button" class="btn btn-outline-success btn-button" value="완료" onclick="regBoardType(this)">
    </td>
  `;
  changeTypeName.insertAdjacentHTML('afterbegin', str);
  selectPart.insertAdjacentHTML('afterbegin', str2);
}

function regBoardType(selectedTag){
  const typeName = selectedTag.parentElement.previousElementSibling.firstElementChild;
  const typeNum = selectedTag.parentElement.previousElementSibling.previousElementSibling;
  
  fetch('/admin/regBoardType', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/json; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: JSON.stringify({
       // 데이터명 : 데이터값
       typeName : typeName.value,
       typeNum : typeNum.textContent
    })
  })
  .then((response) => {
      // return response.text();
      return response.json(); //나머지 경우에 사용
  })
  //fetch 통신 후 실행 영역
  .then((data) => {//data -> controller에서 리턴되는 데이터!
      console.log(data);
  })
  //fetch 통신 실패 시 실행 영역
  .catch(err=>{
      alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
      console.log(err);
  });
  
  
}
