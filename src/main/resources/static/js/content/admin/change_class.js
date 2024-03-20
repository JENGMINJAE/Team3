function goChange(teacheNum, classNum, teachers){
//     const selected_table = document.querySelector('table');
//     selected_table.innerHTML='';
//     const str = `<form action="/admin/updateClass" method="post">
//     <table class="table table-bordered text-center align-middle">
//         <colgroup>
//             <col width="20%">
//             <col width="30%">
//             <col width="20%">
//             <col width="30%">
//         </colgroup>
//         <tr>
//             <td>반 이름</td>
//             <td class="select-className">
//                 <input type="text" name="className" value="${className}">
//             </td>
//             <td>현원/정원</td>
//             <td class="select-personnel">
//                 <input type="number" name="classPersonnel" value="${personnel}" max="20" min="0">
//             </td>
//         </tr>
//         <tr>
//             <td>담당 강사</td>
//             <select name="teacherNum">
//                 <option th:each="teacher : ${teachers}" th:value="${teacher.teacherNum}">[[${teacher.memberVO.memberName}]]</option>
//             </select>
//             <td>강의 기간</td>
//             <td class="select-date">
//                 <div class="row">
//                     <div class="col">시작일</div>
//                     <div class="col">
//                         <input type="date" name="classSdate">
//                     </div>
//                     <div class="col">종료일</div>
//                     <div class="col">
//                         <input type="date" name="classEdate">
//                     </div>
//                 </div>
//             </td>
//         </tr>
//         <tr>
//             <td>상태</td>
//             <td class="select-strEnter">[[${clsInfo.strEnter}]]</td>
//             <td>수강료</td>
//             <td class="select-classPayment">
//                 <input type="number" name="classPayment" value="${payment}">
//             </td>
//         </tr>
//     </table>
// </form>`;
//     selected_className.insertAdjacentHTML('afterbegin', str);

    // const select_personnel = document.querySelector('.select-personnel');
    // const personnel = select_personnel.textContent;
    // select_personnel.innerHTML='';
    // const str1 = `<input type="text" name="classPersonnel" value="${personnel}">`;
    // select_personnel.insertAdjacentHTML('afterbegin', str1);
}