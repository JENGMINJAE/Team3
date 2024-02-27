function btnControl(rBtn){
    const labels = document.querySelectorAll('.aaa');
    for(const label of labels){
        if(label.previousElementSibling.checked){
            
        }
        else{
            label.className = '';
            label.className = 'aaa btn btn-outline-secondary';
        }
    }

    const label_tag = rBtn.nextElementSibling;
    switch (rBtn.value) {
        case '1':
            label_tag.classList.add('btn-outline-success');
            break;
        case '2':
            label_tag.classList.add('btn-outline-danger');
            break;
        case '3':
            label_tag.classList.add('btn-outline-warning');
            break;
        case '4':
            label_tag.classList.add('btn-outline-secondary');
            break;
        case '5':
            label_tag.classList.add('btn-outline-info');
            break;
    }
}