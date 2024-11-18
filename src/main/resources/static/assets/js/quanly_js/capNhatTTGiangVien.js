function hideUpdateInput() {
    let form = document.getElementById('form-container')
    form.classList.add("hidden");

}

hideUpdateInput()

function showUpdateInput() {
    let form = document.getElementById('form-container')
    form.classList.remove("hidden");
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function laydulieuGV(magv) {
    event.preventDefault();
    showUpdateInput()

    fetch(`http://localhost:8080/quanly-giangvien/search?magv=${magv}`).then(response => response.json())
        .then(data => {
            data.forEach(data => {
                addDatatoInput(data);
            })
        })

}

function addDatatoInput(data) {
    const maGV = document.getElementById('masogv');
    const tenGV = document.getElementById('tenGv');
    const gioiTinh = document.getElementById('sex');
    const ngaySinh = document.getElementById('ngaySinh');
    const sdt = document.getElementById('sdt');
    const email = document.getElementById('email');
    const hocvi = document.getElementById('danhHieu');
    const chuyenNganh = document.getElementById('chuyenNganh');
    const tenKhoa = document.getElementById('tenKhoa');
    const kinhNghiemGD = document.getElementById('kinhNghiemGD');

    maGV.value = `${data.maGV}`;
    tenGV.value = `${data.tenGV}`;
    gioiTinh.value = `${data.gioiTinh}`;
    ngaySinh.value = `${data.ngaySinh}`;
    sdt.value = `${data.sdt}`;
    email.value = `${data.email}`;
    hocvi.value = `${data.hocvi}`;
    chuyenNganh.value = `${data.chuyenNganh}`;
    tenKhoa.value = `${data.tenKhoa}`;
    kinhNghiemGD.value = `${data.kinhNghiemGD}`;
}

async function themKhoaHocViChuyenNganh() {
    const selectNganh = document.getElementById('chuyenNganh')
    const selectHocVi = document.getElementById('danhHieu')
    const selectKhoa = document.getElementById('tenKhoa')
    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const {data1} = await responseAPI1.json();
    data1.forEach(data1 => {
        const optionItem = document.createElement('option')
        optionItem.value = `${data1.tenKhoa}`
        optionItem.innerText = `${data1.tenKhoa}`
        selectKhoa.appendChild(optionItem)

    })
    const responseAPI2 = await fetch('http://localhost:8080/hocVi-list');
    const {data4} = await responseAPI2.json();
    data4.forEach(data4 => {
        const optionItem = document.createElement('option')
        optionItem.value = `${data4.hocVi}`
        optionItem.innerText = `${data4.hocVi}`
        selectHocVi.appendChild(optionItem)

    })
    const responseAPI3 = await fetch('http://localhost:8080/chuyenNganh-list');
    const {data3} = await responseAPI3.json();
    data3.forEach(data3 => {
        const optionItem = document.createElement('option')
        optionItem.value = `${data3.chuyenNganh}`
        optionItem.innerText = `${data3.chuyenNganh}`
        selectNganh.appendChild(optionItem)

    })
}

themKhoaHocViChuyenNganh();

function updateGV(event) {
    event.preventDefault();
    const maGV = document.getElementById('masogv').value;
    const tenGV = document.getElementById('tenGv').value;
    const gioiTinh = document.getElementById('sex').value;
    const ngaySinh = document.getElementById('ngaySinh').value;
    const sdt = document.getElementById('sdt').value;
    const email = document.getElementById('email').value;
    const hocvi = document.getElementById('danhHieu').value;
    const chuyenNganh = document.getElementById('chuyenNganh').value;
    const tenKhoa = document.getElementById('tenKhoa').value;
    const kinhNghiemGD = document.getElementById('kinhNghiemGD').value;

    const GVData =
        {
            "maGV": maGV,
            "ngaySinh": ngaySinh,
            "gioiTinh": gioiTinh,
            "sdt": sdt,
            "email": email,
            "hocvi": hocvi,
            "chuyenNganh": chuyenNganh,
            "kinhNghiemGD": kinhNghiemGD,
            "tenKhoa": tenKhoa,
            "tenGV": tenGV
        }

    fetch(`http://localhost:8080/giangvien/${maGV}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(GVData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thông tin giảng viên đã được cập nhật thành công', 'success')
                hideUpdateInput();
                timGVBangDieuKien(event);
            } else {
                showToast(data.message, 'error')
            }
        })
};
document.getElementById('updateGVForm').addEventListener('submit', updateGV)
