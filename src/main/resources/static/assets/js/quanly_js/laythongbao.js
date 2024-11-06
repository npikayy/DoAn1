
async function layThongbao(){
    const list = document.getElementById('thongBaoList')
    list.innerHTML =
        `
    <th>Nội dung thông báo</th>
    <th>Ngày thực hiện</th>
    <th>Người thực hiện</th>
    <th></th>
        `
    const responseAPI = await fetch('http://localhost:8080/thongbao-list');
    const { data } = await responseAPI.json();
    data.forEach(data => {
        const list = document.getElementById('thongBaoList')
        const divItem2 = document.createElement('tr')
        let idTbao = data.idTbao;
        divItem2.innerHTML=`
        <td>${data.noiDungTbao}</td>
        <td>${data.ngayThucHien}</td>
        <td>${data.nguoiThucHien}</td>
        <td><button id="xoaBtn" onclick="xoaTBao('${idTbao}')">xóa</button></td>
        `
        list.appendChild(divItem2)

    });
}
function xoaTBao(idTBao) {
    fetch(`http://localhost:8080/thongbao/${idTBao}`, {method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                layThongbao();
            }
        })
}


