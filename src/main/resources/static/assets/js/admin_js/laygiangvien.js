
async function layGV(){
    const list = document.getElementById('userList')
    const responseAPI = await fetch('http://localhost:8080/teacher-list');
    const {data} = await responseAPI.json();
        data.forEach(data => {
            const divItem2 = document.createElement('tr')
            divItem2.innerHTML=`
        <td>${data.maGV}</td>
        <td>${data.tenGV}</td>
        <td>${data.ngaySinh}</td>
        <td>${data.email}</td>
        <td>${data.tenKhoa}</td>
        <td>${data.maKhoa}</td>
        <td>${data.tenNganh}</td>
        `
            list.appendChild(divItem2)
        });
}
async function timGVbangMagv(event) {
    event.preventDefault()
    const list = document.getElementById('userList')
    let magv = document.getElementById('search').value
    fetch(`http://localhost:8080/quanly-giangvien/search?magv=${magv}`)
        .then(response => response.json())
        .then(data => {
            list.innerHTML =
                `
        <tr>
        <th>Mã giảng viên</th>
        <th>Tên giảng viên</th>
        <th>Ngày sinh</th>
        <th>Email</th>
        <th>Tên khoa</th>
        <th>Mã khoa</th>
        <th>Tên ngành</th>
        </tr>
    `
            data.forEach(data => {
                const divItem2 = document.createElement('tr')
                divItem2.innerHTML = `
        <td>${data.maGV}</td>
        <td>${data.tenGV}</td>
        <td>${data.ngaySinh}</td>
        <td>${data.email}</td>
        <td>${data.tenKhoa}</td>
        <td>${data.maKhoa}</td>
        <td>${data.tenNganh}</td>
        `
                list.appendChild(divItem2)
            });
        })
}

