document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const fileInput = document.getElementById('upload-button');
    formData.append('file', fileInput.files[0]);
    if (fileInput.files.length === 0) {
        showToast('Vui lòng chọn 1 tệp trước khi nhấn nút!', 'error');
    } else {
    fetch('http://localhost:8080/giangvien/upload', {
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
async function themKhoaHocViChuyenNganh() {
    const selectNganh = document.getElementById('chuyenNganh')
    const selectHocVi = document.getElementById('danhHieu')
    const selectKhoa = document.getElementById('tenKhoa')
    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const { data1 } = await responseAPI1.json();
    data1.forEach(data1 => {
        const optionItem = document.createElement('option')
        optionItem.value=`${data1.tenKhoa}`
        optionItem.innerText=`${data1.tenKhoa}`
        selectKhoa.appendChild(optionItem)

    })
    const responseAPI2 = await fetch('http://localhost:8080/hocVi-list');
    const { data4 } = await responseAPI2.json();
    data4.forEach(data4 => {
        const optionItem = document.createElement('option')
        optionItem.value=`${data4.hocVi}`
        optionItem.innerText=`${data4.hocVi}`
        selectHocVi.appendChild(optionItem)

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
themKhoaHocViChuyenNganh();
function taoGVmoi(event) {
    event.preventDefault();
    const maGV = document.getElementById('masogv').value;
    const tenGV = document.getElementById('tenGv').value;
    const ngaySinh = document.getElementById('ngaySinh').value;
    const email = document.getElementById('email').value;
    const hocvi = document.getElementById('danhHieu').value;
    const chuyenNganh = document.getElementById('chuyenNganh').value;
    const tenKhoa = document.getElementById('tenKhoa').value;
    const kinhNghiemGD = document.getElementById('kinhNghiemGD').value;

    const GVData =
        {
            "maGV": maGV,
            "ngaySinh": ngaySinh,
            "email": email,
            "hocvi": hocvi,
            "chuyenNganh": chuyenNganh,
            "kinhNghiemGD": kinhNghiemGD,
            "tenKhoa": tenKhoa,
            "tenGV": tenGV
        }

    fetch(`http://localhost:8080/taoGVMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(GVData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code===1000){
                showToast('Tạo giảng viên thành công', 'success')
            }
            else{
                showToast(data.message, 'error')
            }
    })
}
document.getElementById('createGVForm').addEventListener('submit', taoGVmoi)

