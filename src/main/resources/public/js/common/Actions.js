showResultModal = (text, type, onclick = null) => {
    console.log(text, type);
    let className = "btn btn-primary";
    if (type === "ERROR") {
        className = "btn btn-danger";
    } else if (type === "SUCCESS") {
        className = "btn btn-success";
    }
    let button = document.getElementById('resultRegistrationModalButton');
    button.onclick = onclick;
    button.className = className;
    document.getElementById('resultRegistrationModalText').innerHTML = text;
    $("#infoModal").modal();
}
