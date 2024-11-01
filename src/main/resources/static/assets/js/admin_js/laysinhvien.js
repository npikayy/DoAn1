async function layKhoaNienKhoaChuyenNganh() {
    const selectNganh = document.getElementById('nganh')
    const selectnienKhoa = document.getElementById('nienKhoa')
    const selectKhoa = document.getElementById('khoa')
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
layKhoaNienKhoaChuyenNganh();
async function timSVBangDieuKien(event) {
    event.preventDefault()
    let khoa = document.getElementById('khoa').value
    let nganh = document.getElementById('nganh').value
    let nienKhoa = document.getElementById('nienKhoa').value
    let search = document.getElementById('search').value
    let tenOrmssv = document.getElementById('tensvOrmssv').value

    let url = 'http://localhost:8080/quanly-sinhvien/search?';

    if (khoa !== 'none') { url += `tenKhoa=${khoa}&`; }

    if (nganh !== 'none') { url += `chuyenNganh=${nganh}&`; }

    if (nienKhoa !== 'none') { url += `nienKhoa=${nienKhoa}&`; }

    if (search !== '') {
        if (tenOrmssv === 'tenSV') { url += `tenSV=${search}&`;
        }
    else {
        url += `mssv=${search}&`; }
    }
    url = url.slice(0, -1);

    if (url === 'http://localhost:8080/quanly-sinhvien/search')
    { const responseAPI = await fetch('http://localhost:8080/sinhvien-list');
        const { data } = await responseAPI.json(); addTabletitle();
        data.forEach(data => { addDatatoTable(data); }); }
    else { fetch(url) .then(response => response.json())
        .then(data => { addTabletitle(); data.forEach(data => { addDatatoTable(data); }); })
        .catch(error => console.error('Error:', error));
    }
}

function addTabletitle(){
    const list = document.getElementById('userList')
    list.innerHTML =
        `
       <th>Mã số sinh viên</th>
       <th>Tên sinh viên</th>
       <th>Ngày sinh</th>
       <th>Số điện thoại</th>
       <th>Email</th>
       <th>Lớp</th>
       <th>Niên khóa</th>
       <th>Ngành</th>
       <th>Khoa</th>
   `
}
function addDatatoTable(data){
    const list = document.getElementById('userList')
    const divItem2 = document.createElement('tr')
    divItem2.innerHTML = `
       <td>${data.mssv}</td>
        <td>${data.tenSV}</td>
        <td>${data.ngaySinh}</td>
        <td>${data.sdt}</td>
        <td>${data.email}</td>
        <td>${data.lop}</td>
        <td>${data.nienKhoa}</td>
        <td>${data.chuyenNganh}</td>
        <td>${data.tenKhoa}</td>
       `
    list.appendChild(divItem2)
}