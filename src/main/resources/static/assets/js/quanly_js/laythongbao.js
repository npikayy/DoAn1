function extractDateComponentsFromNgaySinh(ngaySinh) {
    const date = new Date(ngaySinh);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return { day, month, year };
}
async function layThongbao(){
    const responseAPI = await fetch('http://localhost:8080/thongbao-list');
    const { data } = await responseAPI.json();
    data.forEach(data => {
        const list = document.getElementById('thongBaoList')
        const divItem2 = document.createElement('tr')
        const { day, month, year } = extractDateComponentsFromNgaySinh(data.ngayThucHien);
        divItem2.innerHTML=`
        <td>${data.noiDungTbao}</td>
        <td>${data.nguoiThucHien}</td>
        <td>${day}/${month}/${year}</td>
        `
        list.appendChild(divItem2)

    });
}
