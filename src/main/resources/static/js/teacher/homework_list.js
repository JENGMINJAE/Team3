function homework_crystal(thishc){
    let is_sure = confirm("수정하시겠습니까?")
    const hc = thishc.parentElement.firstElementChild.value
    if(is_sure){
        const ChwNum = document.querySelector("#C_hwNum");
        ChwNum.querySelector('input').value = hc;
        alert(ChwNum.querySelector('input').value)
    }
}

function homework_delete(thishd){
    let is_sure = confirm("삭제하시겠습니까?")
    const hd = thishd.parentElement.firstElementChild.value
    if(is_sure){
        const DhwNum = document.querySelector("#D_hwNum");
        DhwNum.querySelector('input').value = hd;
        DhwNum.submit();
    }
}
