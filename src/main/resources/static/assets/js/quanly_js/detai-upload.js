document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fileInput = document.getElementById('upload-button');
    formData.append('file', fileInput.files[0]);
    if (fileInput.files.length === 0) {
        showToast('Vui lòng chọn 1 tệp trước khi nhấn nút!', 'error');
    } else {
    fetch('http://localhost:8080/detai/upload', {
        method: 'POST',
        body: formData
    }).then(res => res.json()).then(data => {
        console.log(data)
        if (data.code === 0) {
            showToast(data.result, 'success');
        }
        else{
            showToast(data.result, 'error');
        }
    })
    }
})


