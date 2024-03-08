document.querySelector("#currentDate").value = new Date().toISOString().substring(0, 10);



const class_select = document.querySelector('#class_select');
class_select.onchange = function(){
    const stu_select = document.querySelector('#stu_select');
    let main_option = class_select.options[class_select.selectedIndex].value;
    alert(main_option);
}

const stu_options = {

};