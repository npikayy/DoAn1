document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fileInput = document.getElementById('upload-button');
    formData.append('file', fileInput.files[0]);

    fetch('http://localhost:8080/upload/excel', {
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


let uploadButton = document.getElementById("upload-button");
let container = document.querySelector(".container");
let error = document.getElementById("error");
let fileDisplay = document.getElementById("file-display");
const fileHandler = (file, name, type) => {
    if (type !== "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") {
        error.innerText = "Please upload an Excel file";
        return false;
    }
    error.innerText = "";
    let reader = new FileReader();
    reader.readAsArrayBuffer(file);
    reader.onloadend = async () => {
        let fileContainer = document.createElement("div");
        fileContainer.classList.add("excel-file");
        let fileSize = (file.size / 1024).toFixed(2);
        fileContainer.innerHTML = `
    <img src="/assets/images/sheet.png" alt="Excel Icon" class="file-icon">
    <p>File: ${name} (${fileSize} KB)</p>
    `;
        fileDisplay.appendChild(fileContainer)
    }
}
//Upload Button
uploadButton.addEventListener("change", () => {
    fileDisplay.innerHTML = "";
    Array.from(uploadButton.files).forEach((file) => {
        fileHandler(file, file.name, file.type);
    });
});

container.addEventListener(
    "dragenter",
    (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.classList.add("active");
    },
    false
);

container.addEventListener(
    "dragleave",
    (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.classList.remove("active");
    },
    false
);

container.addEventListener(
    "dragover",
    (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.classList.add("active");
    },
    false
);

container.addEventListener(
    "drop",
    (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.classList.remove("active");
        let draggedData = e.dataTransfer;
        let files = draggedData.files;
        uploadButton.files = files;
        fileDisplay.innerHTML = "";
        Array.from(files).forEach((file) => {
            fileHandler(file, file.name, file.type);
        });
    },
    false
);

window.onload = () => {
    error.innerText = "";
};