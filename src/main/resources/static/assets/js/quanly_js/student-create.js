document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fileInput = document.getElementById('upload-button');
    formData.append('file', fileInput.files[0]);
    if (fileInput.files.length === 0) {
        showToast('Vui lòng chọn 1 tệp trước khi nhấn nút!', 'error');
    } else {
        fetch('http://localhost:8080/student/upload', {
            method: 'POST',
            body: formData
        }).then(res => res.json()).then(data => {
            console.log(data)
            if (data.code === 0) {
                showToast(data.message, 'success');
            }
            else{
                showToast(data.message, 'error');
            }
        })
    }
})
function hideCreateSection(){
    let createSection = document.getElementById('create-section')
    let uploadSection = document.getElementById('upload-section')
    createSection.classList.add("hidden");
    uploadSection.classList.remove("hidden")
}
hideCreateSection()
function unhideCreateSection(){
    let createSection = document.getElementById('create-section')
    let uploadSection = document.getElementById('upload-section')
    createSection.classList.remove("hidden");
    uploadSection.classList.add("hidden");
}
async function themKhoaNienKhoaChuyenNganh() {
    const selectNganh = document.getElementById('chuyenNganh')
    const selectnienKhoa = document.getElementById('khoaHoc')
    const selectKhoa = document.getElementById('tenKhoa')
    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const { data1 } = await responseAPI1.json();
    data1.forEach(data1 => {
        const optionItem = document.createElement('option')
        optionItem.value=`${data1.tenKhoa}`
        optionItem.innerText=`${data1.tenKhoa}`
        selectKhoa.appendChild(optionItem)

    })
    const responseAPI2 = await fetch('http://localhost:8080/nienKhoa-list');
    const { data2 } = await responseAPI2.json();
    data2.forEach(data2 => {
        const optionItem = document.createElement('option')
        optionItem.value=`${data2.nienKhoa}`
        optionItem.innerText=`${data2.nienKhoa}`
        selectnienKhoa.appendChild(optionItem)

    })
    const responseAPI3 = await fetch('http://localhost:8080/chuyenNganh-list');
    const { data3 } = await responseAPI3.json();
    data3.forEach(data3 => {
        const optionItem = document.createElement('option')
        optionItem.value=`${data3.chuyenNganh}`
        optionItem.innerText=`${data3.chuyenNganh}`
        selectNganh.appendChild(optionItem)

    })
}
themKhoaNienKhoaChuyenNganh();
function taoSVMoi(event) {
    event.preventDefault();
    const masosv = document.getElementById('masosv').value;
    const tenSV = document.getElementById('tenSV').value;
    const ngaySinh = document.getElementById('ngaySinh').value;
    let sdt = document.getElementById('sdt').value;
    const email = document.getElementById('email').value;
    const lop = document.getElementById('lop').value;
    const nienKhoa = document.getElementById('khoaHoc').value;
    const chuyenNganh = document.getElementById('chuyenNganh').value;
    const tenKhoa = document.getElementById('tenKhoa').value;

    const value = sdt;
    sdt = value.replace(/[^0-9]/g, '');

    const studentData =
        {   "mssv" : masosv,
            "ngaySinh": ngaySinh,
            "email": email,
            "nienKhoa": nienKhoa,
            "chuyenNganh": chuyenNganh,
            "tenSV": tenSV,
            "mssv": masosv,
            "lop": lop,
            "sdt": sdt,
            "tenKhoa": tenKhoa
        }

    fetch(`http://localhost:8080/taoSVMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(studentData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code===1000){
                showToast('Tạo sinh viên thành công', 'success')
            }
            else{
                showToast(data.message, 'error')
            }
        })
        .catch(error => {

        });
};
document.getElementById('createSVForm').addEventListener('submit', taoSVMoi)




