async function layKhoaHocViChuyenNganh() {
    const selectNganh = document.getElementById('nganh')
    const selectHocVi = document.getElementById('hocvi')
    const selectKhoa = document.getElementById('khoa')
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
layKhoaHocViChuyenNganh();
async function timGVBangDieuKien(event) {
    event.preventDefault()
    let khoa = document.getElementById('khoa').value
    let nganh = document.getElementById('nganh').value
    let hocvi = document.getElementById('hocvi').value
    let search = document.getElementById('search').value
    let tenOrmaGV = document.getElementById('tenGVorMagv').value

    let url = 'http://localhost:8080/quanly-giangvien/search?';

    if (khoa !== 'none') { url += `tenKhoa=${khoa}&`; }

    if (nganh !== 'none') { url += `chuyenNganh=${nganh}&`; }

    if (hocvi !== 'none') { url += `hocvi=${hocvi}&`; }

    if (search !== '') {
        if (tenOrmaGV === 'tenGV') { url += `tenGV=${search}&`;
        }
        else {
            url += `magv=${search}&`; }
    }
    url = url.slice(0, -1);

    if (url === 'http://localhost:8080/quanly-giangvien/search')
    { const responseAPI = await fetch('http://localhost:8080/giangVien-list');
        const { data } = await responseAPI.json(); addTabletitle();
        data.forEach(data => { addDatatoTable(data); }); }
    else { fetch(url) .then(response => response.json())
        .then(data => { addTabletitle(); data.forEach(data => { addDatatoTable(data); }); })
        .catch(error => console.error('Error:', error));
    }
}
function extractDateComponentsFromNgaySinh(ngaySinh) {
    const date = new Date(ngaySinh);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return { day, month, year };
}
function addTabletitle(){
    const list = document.getElementById('userList')
    list.innerHTML =
        `
       <th>Mã giảng viên</th>
       <th>Tên giảng viên</th>
       <th>Ngày sinh</th>
       <th>Email</th>
       <th>Học vị</th>
       <th>Chuyên ngành</th>
       <th>Khoa</th>
       <th>Kinh nghiệm giảng dạy</th>
       <th></th>
        `
}
function addDatatoTable(data){
    const list = document.getElementById('userList')
    const divItem2 = document.createElement('tr')
    const { day, month, year } = extractDateComponentsFromNgaySinh(data.ngaySinh);
    let email = data.email;
    let magv = data.maGV
    divItem2.innerHTML=`
        <td>${data.maGV}</td>
        <td>${data.tenGV}</td>
        <td>${day}/${month}/${year}</td>
        <td>${data.email}</td>
        <td>${data.hocvi}</td>
        <td>${data.chuyenNganh}</td>
        <td>${data.tenKhoa}</td>
        <td>${data.kinhNghiemGD}</td>
        <td>
            <div>
                <button onclick="laydulieuGV('${magv}')">sửa</button>
                <button onclick="xoaGV('${magv}')">xóa</button>
            </div>
        </td>
        `
    list.appendChild(divItem2)
}

function xoaGV(magv){
    fetch(`http://localhost:8080/giangvien/${magv}`,{method: 'DELETE'})
        .then(response => { if (response.ok)
        {
            showToast('Xóa giảng viên thành công', 'success');
            timGVBangDieuKien(new Event('fetch'));
        }
        })
}
