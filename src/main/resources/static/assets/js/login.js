function login(event){
    event.preventDefault();
    const username = document.getElementById('username').value
    const password = document.getElementById('password').value
    const newUser = {
        "username" : username,
        "password" : password,
    }
    fetch(`http://localhost:8080/auth/log-in`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(newUser)
    }).then(res=> res.json()).then(data =>{
        console.log(data)
        if (data.code!==0){
            alert(data.message)
        }
        else if (data.result.authenticated===true){
            const toast = document.createElement('div')
            toast.id = 'toast'
            toast.innerHTML = `        
        <div class="toast toast__success">
            <div class="toast__icon">
                <i class="fa-solid fa-circle-check"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Success</h3>
                <p class="toast__msg">
                    Đăng nhập thành công!.
                </p>
            </div>
            <div class="toast__close">
                <i class="fa-solid fa-xmark"></i>
            </div>
        </div>
            `
            document.body.appendChild(toast)
            setTimeout(() => {
                window.location.href = "http://localhost:8080/get_all_users";
            }, 1000);
        }
        else{
            const toast = document.createElement('div')
            toast.id = 'toast'
            toast.innerHTML = `
        <div class="toast toast__error">
            <div class="toast__icon">
                <i class="fa-solid fa-circle-exclamation"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Error</h3>
                <p class="toast__msg">
                     Đăng nhập thất bại!, vui lòng kiểm tra lại tài khoản hoặc mật khẩu.
                </p>
            </div>
            <div class="toast__close">
                <i class="fa-solid fa-xmark"></i>
            </div>
        </div>
            `
            document.body.appendChild(toast)
        }
    })
}
document.getElementById('log_in_form').addEventListener('submit', login);

document.getElementById('username').focus()

history.pushState(null, '', location.href); // Push new history entry to stack
window.addEventListener('popstate', (e) => {
    console.log(e)
    history.go(1);
})




