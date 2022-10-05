const registerForwardButton = document.querySelectorAll(".account-button")[0];

registerForwardButton.onclick =() =>{
    location.href = "/account/register"; /*절대 경로 슬래시 붙여야함 내자신서버에서 실행하는 */
}

