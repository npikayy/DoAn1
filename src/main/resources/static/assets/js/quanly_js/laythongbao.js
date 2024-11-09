
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

function AnThongBao(){
    let form = document.getElementById('thongbao-section')
    let btn = document.getElementById('hientbaoBtn')
    let bieudo = document.getElementById('bieudo-section')
    form.classList.add("hidden");
    btn.classList.remove("hidden");
    bieudo.classList.remove("hidden");
}
function HienThongBao(){
    let form = document.getElementById('thongbao-section')
    let btn = document.getElementById('hientbaoBtn')
    let bieudo = document.getElementById('bieudo-section')
    form.classList.remove("hidden");
    btn.classList.add("hidden");
    bieudo.classList.add("hidden");
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
async function FetchAndcreateChart() {
    const response = await fetch('http://localhost:8080/detai-list');
    const result = await response.json();
    console.log(result);
    const data = result.data;
    const counts = {};
    data.forEach(d => {
        if (!counts[d.tenKhoa]) {
            counts[d.tenKhoa] = { hoanthanh: 0, chuahoanthanh: 0
            };
        }
        if (d.tinhtrang === "Đã hoàn thành") {
            counts[d.tenKhoa].hoanthanh += 1;
        }
        else {
            counts[d.tenKhoa].chuahoanthanh += 1;
        }
    });
    const labels = Object.keys(counts);
    // const values = Object.values(counts);
    const hoanthanhValues = labels.map(label => counts[label].hoanthanh);
    console.log(hoanthanhValues)
    const chuahoanthanhValues = labels.map(label => counts[label].chuahoanthanh);
    console.log(chuahoanthanhValues)
    const ctx = document.getElementById('myChart').getContext('2d');

    // console.log(labels)
    // console.log(values)

    const myChart = new Chart(ctx, { type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Hoàn thành',
                    data: hoanthanhValues,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Chưa hoàn thành',
                    data: chuahoanthanhValues,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            scales: { y:
                    { beginAtZero: true } } } }); }
FetchAndcreateChart()
