
async function laySV(){
    const list = document.getElementById('userList')
    const responseAPI = await fetch('http://localhost:8080/student-list');
    const {data} = await responseAPI.json();
    data.forEach(data => {
            const divItem2 = document.createElement('tr')
            divItem2.innerHTML=`
        <td>${data.mssv}</td>
        <td>${data.tenSV}</td>
        <td>${data.ngaySinh}</td>
        <td>${data.sdt}</td>
        <td>${data.email}</td>
        <td>${data.lop}</td>
        <td>${data.nienKhoa}</td>
        <td>${data.maNhom}</td>
        `
            list.appendChild(divItem2)
        });
}
async function timSVbangMssv(event) {
    event.preventDefault()
    const list = document.getElementById('userList')
    let mssv = document.getElementById('search').value
    fetch(`http://localhost:8080/quanly-sinhvien/search?mssv=${mssv}`)
        .then(response => response.json())
        .then(data => {
            list.innerHTML =
                `
        <tr>
         <th>Mã sinh viên</th>
         <th>Tên sinh viên</th>
         <th>Ngày sinh</th>
         <th>Số điện thoại</th>
         <th>Email</th>
         <th>Lớp</th>
          <th>Niên khóa</th>
          <th>Mã nhóm</th>
           </tr>
    `
            data.forEach(data => {
                const divItem2 = document.createElement('tr')
                divItem2.innerHTML = `
        <td>${data.mssv}</td>
        <td>${data.tenSV}</td>
        <td>${data.ngaySinh}</td>
        <td>${data.sdt}</td>
        <td>${data.email}</td>
        <td>${data.lop}</td>
        <td>${data.nienKhoa}</td>
        <td>${data.maNhom}</td>
        `
                list.appendChild(divItem2)
            });
        })
}

