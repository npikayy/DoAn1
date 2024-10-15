document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fileInput = document.getElementById('upload-button');
    formData.append('file', fileInput.files[0]);

    fetch('http://localhost:8080/student/upload', {
        method: 'POST',
        body: formData
    }).then(res => res.json()).then(data => {
        console.log(data)
        if (data.code === 0) {
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
                   ${data.result} 
                </p>
            </div>
            <div class="toast__close">
                <i class="fa-solid fa-xmark"></i>
            </div>
        </div>
            `
            document.body.appendChild(toast)
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
                ${data.result}
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
})


