function hideUpdateInput(){
    let form = document.getElementById('form-container')
    form.classList.add("hidden");

}
hideUpdateInput()
function showUpdateInput(){
    let form = document.getElementById('form-container')
    form.classList.remove("hidden");
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
function laydulieuSV(mssv) {
    event.preventDefault();
    showUpdateInput()
     fetch(`http://localhost:8080/quanly-sinhvien/search?mssv=${mssv}`) .then(response => response.json())
         .then(data => {
             data.forEach(data => { addDatatoInput(data);
             })
         })

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
function addDatatoInput(data){
    const mssv = document.getElementById('masosv');
    const tenSV = document.getElementById('tenSV');
    const gtinh = document.getElementById('sex');
    const ngaySinh = document.getElementById('ngaySinh');
    const sdt = document.getElementById('sdt');
    const email = document.getElementById('email');
    const lop = document.getElementById('lop');
    const nienKhoa = document.getElementById('khoaHoc');
    const chuyenNganh = document.getElementById('chuyenNganh');
    const tenKhoa = document.getElementById('tenKhoa');

    mssv.value=`${data.mssv}`
    tenSV.value=`${data.tenSV}`
    gtinh.value=`${data.gioiTinh}`
    ngaySinh.value=`${data.ngaySinh}`
    sdt.value=`${data.sdt}`
    email.value=`${data.email}`
    lop.value=`${data.lop}`
    nienKhoa.value=`${data.nienKhoa}`
    chuyenNganh.value=`${data.chuyenNganh}`
    tenKhoa.value=`${data.tenKhoa}`

}


    function updateSV(event) {
        event.preventDefault();
        const masosv = document.getElementById('masosv').value;
        const tenSV = document.getElementById('tenSV').value;
        const gtinh = document.getElementById('sex').value;
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
            {
                "ngaySinh": ngaySinh,
                "email": email,
                "gioiTinh": gtinh,
                "nienKhoa": nienKhoa,
                "chuyenNganh": chuyenNganh,
                "tenSV": tenSV,
                "mssv": masosv,
                "lop": lop,
                "sdt": sdt,
                "tenKhoa": tenKhoa
            }

        fetch(`http://localhost:8080/sinhvien/${masosv}`, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify(studentData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 1000) {
                    showToast('Thông tin sinh viên đã được cập nhật thành công', 'success')
                    hideUpdateInput();
                    timSVBangDieuKien(event);
                } else {
                    showToast(data.message, 'error')
                }
            })
    }
document.getElementById('updateSVForm').addEventListener('submit', updateSV)
