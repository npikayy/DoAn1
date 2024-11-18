function saveLoginInfo() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const rememberMe = document.getElementById("rememberMe").checked;
    if (rememberMe) {
        localStorage.setItem("username", username);
        localStorage.setItem("password", password);
        localStorage.setItem("rememberMe", "true");
    } else {
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        localStorage.removeItem("rememberMe");
    }
}

function loadLoginInfo() {
    const rememberMe = localStorage.getItem("rememberMe");
    if (rememberMe === "true") {
        document.getElementById("username").value = localStorage.getItem("username");
        document.getElementById("password").value = localStorage.getItem("password");
        document.getElementById("rememberMe").checked = true;

    }
}

document.addEventListener("DOMContentLoaded", function () {
    loadLoginInfo();

    const loginForm = document.getElementById("loginForm");
    loginForm.addEventListener("submit", saveLoginInfo);

    const params = new URLSearchParams(window.location.search);
    const messageDiv = document.getElementById("message");

    if (params.has("logout")) {
        messageDiv.innerHTML = "Bạn đã đăng xuất thành công!";
    }
    if (params.has("error")) {
        messageDiv.innerHTML = "Tên người dùng hoặc mật khẩu không đúng!";
    }
});