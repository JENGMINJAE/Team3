
const bigyo = () => {
  const Sdate = document.querySelector("#Sdate").value;
  const Edate = document.querySelector("#Edate").value;

  if(Sdate > Edate){
    alert('시작 날짜가 종료 날짜보다 뒤입니다');
    return;
  }
  else{
    document.querySelector("#formform").submit();
  }
  
}