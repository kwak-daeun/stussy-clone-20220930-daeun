const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInputs = document.querySelectorAll(".account-input");
    let user = {
        lastName: accountInputs[0].value,
        firstName: accountInputs[1].value,
        email: accountInputs[2].value,
        password: accountInputs[3].value
    }

    //JSON stringify() ->js 객체를 json문자열로 변환
    //JSON.parse() -> JSON문자열을 js객체로 변환

    let ajaxOption = { //객체
        async: false,  //key, value                              //필수
        type: "post",                                           //필수
        url: "/api/account/register",                            //필수
        contentType: "application/json",                        //전송할 데이터가 json인 경우                
        data: JSON.stringify(user),                             //전송할 데이터가 있으면
        dataType: "json", //return타입
        success: (response, textStatus, request) =>{ //괄호 안에는 메소드              //json와 text 등을 사용할 수 있지만 js사용함
           console.log(response);
           const successURI = request.getResponseHeader("location");
           location.replace(successURI + "?email="  + response.data);
           //성공시에 실행될 메소드
        },
        error: (error) => {                                     //실패시 실행될 메소드

            console.log(error.responseJSON.data);
            loadErrorMessage(error.responseJSON.data);
            //메소드 호출
        }

    }

    $.ajax(ajaxOption); //달러.ajax호출

}

function loadErrorMessage(errors){
    const errorList = document.querySelector(".errors");
    const errorMsgs = document.querySelector(".error-msgs");
    const errorArray = Object.values(errors);
    //인덱스가 없기 때문에 object.values를 써준다

    errorMsgs.innerHTML=""; //초기화 실행될때마다 비움

    errorArray.forEach(error => {
        errorMsgs.innerHTML += `
        <li>${error}</li>
        `;
    });

    errorList.classList.remove("errors-invisible");
}