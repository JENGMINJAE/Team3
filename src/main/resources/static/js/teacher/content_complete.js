function look(thisDetail,content){
    const className = thisDetail.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    const stuName = thisDetail.parentElement.previousElementSibling.previousElementSibling.textContent;
    const cstDate = thisDetail.parentElement.previousElementSibling.textContent;
    const plusHere = document.querySelector(".here");
    let str = '';
    plusHere.innerHTML = '';
    str+=`<table class="table" id="detail" style="text-align: center; vertical-align: middle;">
            <thead>
                <tr>
                    <td>소속</td>
                    <td>이름</td>
                    <td>상담일</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>`;
                    str+=className;
                    str+=`</td>
                    <td>`
                    str+=stuName;
                    str+=`</td>
                    <td>`
                    str+=cstDate;
                    str+=`</td>
                </tr>
                <tr>
                    <td colspan="3">
                        `;
                        str+=content
                        str+=`
                    </td>
                </tr>
            </tbody>
        </table>`;
    plusHere.insertAdjacentHTML("afterbegin",str)
}