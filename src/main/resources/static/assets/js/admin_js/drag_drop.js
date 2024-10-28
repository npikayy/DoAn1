let uploadButton = document.getElementById("upload-button");
let container = document.querySelector(".container");
let error = document.getElementById("error");
let fileDisplay = document.getElementById("file-display");
const fileHandler = (file, name, type) => {
    if (type !== "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") {
        error.innerText = "Vui lòng sử dụng tệp định dạng Excel";
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