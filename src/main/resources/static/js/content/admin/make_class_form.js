

function changeClsInfo(classNum){
    location.href=`/admin/goClassInfo?classNum=${classNum}`;
}

function submitClass(){
    const chk_clsName = document.querySelector('input[name="className"]').value;
    const chk_payment = document.querySelector('input[name="classPayment"]').value;
    const chk_personnel = document.querySelector('input[name="classPersonnel"]').value;
    const chk_classSdate = document.querySelector('input[name="classSdate"]').value;
    const chk_classEdate = document.querySelector('input[name="classEdate"]').value;
    let today =new Date().toISOString().substring(0, 10);

    if(chk_clsName == null || chk_clsName == ""){
        alert('반명을 입력하세요.');
        return;
    }

    if(chk_payment == null || chk_payment == ""){
        alert('수강료를 입력하세요.');
        return;
    }

    if(chk_personnel == null || chk_personnel == ""){
        alert('정원을 입력하세요.');
        return;
    }

    if(chk_classSdate == null || chk_classSdate == ""){
        alert('강의 시작일을 입력하세요.');
        return;
    }

    if(chk_classSdate < today){
        alert('강의 시작일을 다시 선택해주세요.');
        return;
    } else if(chk_classEdate < today){
        alert('강의 종료일을 다시 선택해주세요.');
        return;
    }

    if(chk_classSdate > chk_classEdate){
        alert('강의일을 다시 선택해주세요.');
        return;
    }

    if(chk_classEdate == null || chk_classEdate == ""){
        alert('강의 종료일을 다시 선택해주세요.');
        return;
    }
    else{
        const setData=document.querySelector('form').submit();
    }
}