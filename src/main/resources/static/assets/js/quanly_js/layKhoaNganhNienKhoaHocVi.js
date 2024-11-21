async function layKhoaHocViChuyenNganhNienKhoa(event) {
    event.preventDefault()
    const Nganh = document.getElementById('nganhList')
    const HocVi = document.getElementById('hocviList')
    const Khoa = document.getElementById('khoaList')
    const nienKhoa = document.getElementById('nienkhoaList')

    const n = document.getElementById('nganh')
    const hv = document.getElementById('hocvi')
    const k = document.getElementById('tenkhoa')
    const nk = document.getElementById('nienkhoa')

    n.value = ""
    nk.value = ""
    hv.value = ""
    k.value = ""

    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const {data1} = await responseAPI1.json();
    Khoa.innerHTML =
        `
       <th>Danh sách khoa</th>
       <th></th>
        `
    data1.forEach(data1 => {
        // addTableKhoatitle()
        const divItem2 = document.createElement('tr')
        let tenkhoa = data1.tenKhoa;
        divItem2.innerHTML = `
        <td>${data1.tenKhoa}</td>
        <td>
            <button id="xoaBtn" onclick="xoaItem('khoa','${tenkhoa}')">xóa</button>
        </td>
        `
        Khoa.appendChild(divItem2)
    })
    const responseAPI2 = await fetch('http://localhost:8080/nienKhoa-list');
    const {data2} = await responseAPI2.json();
    nienKhoa.innerHTML =
        `
       <th>Danh sách niên khóa</th>
       <th></th>
        `
    data2.forEach(data2 => {
        const divItem2 = document.createElement('tr')
        let nienkhoa = data2.nienKhoa;
        divItem2.innerHTML = `
        <td>${data2.nienKhoa}</td>
        <td>
            <button id="xoaBtn" onclick="xoaItem('nienkhoa','${nienkhoa}')">xóa</button>
        </td>
        `
        nienKhoa.appendChild(divItem2)
    })
    const responseAPI3 = await fetch('http://localhost:8080/chuyenNganh-list');
    const {data3} = await responseAPI3.json();
    Nganh.innerHTML =
        `
       <th>Danh sách chuyên ngành</th>
       <th></th>
        `
    data3.forEach(data3 => {
        const divItem2 = document.createElement('tr')
        let nganh = data3.chuyenNganh;
        divItem2.innerHTML = `
        <td>${data3.chuyenNganh}</td>
        <td>
            <button id="xoaBtn" onclick="xoaItem('nganh', '${nganh}')">xóa</button>
        </td>
        `
        Nganh.appendChild(divItem2)
    })
    const responseAPI4 = await fetch('http://localhost:8080/hocVi-list');
    const {data4} = await responseAPI4.json();
    HocVi.innerHTML =
        `
       <th>Danh sách học vị</th>
       <th></th>
        `
    data4.forEach(data4 => {
        const divItem2 = document.createElement('tr')
        let hocvi = data4.hocVi;
        divItem2.innerHTML = `
        <td>${data4.hocVi}</td>
        <td>
            <button id="xoaBtn" onclick="xoaItem('hocvi', '${hocvi}')">xóa</button>
        </td>
        `
        HocVi.appendChild(divItem2)
    })

}


function taoKhoaMoi(event) {
    event.preventDefault();
    let tenKhoa = document.getElementById('tenkhoa').value;
    if (/\d/.test(tenKhoa)) {
        tenKhoa = tenKhoa.replace(/\d/g, '');
    }
    if (/\s+/.test(tenKhoa)) {
        tenKhoa = tenKhoa.replace(/\s+/g, '');
    }
    tenKhoa = tenKhoa.toUpperCase();
    tenKhoa = tenKhoa.normalize('NFD').replace(/[\u0300-\u036f]/g, '').replace(/[^a-zA-Z0-9 ]/g, '');
    const KhoaData =
        {
            "tenKhoa": tenKhoa
        }

    fetch(`http://localhost:8080/taoKhoaMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(KhoaData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thêm khoa mới thành công', 'success')
                layKhoaHocViChuyenNganhNienKhoa(event);
            } else {
                showToast(data.message, 'error')
            }
        })
}

document.getElementById('themKhoaForm').addEventListener('submit', taoKhoaMoi)

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function taoNienKhoaMoi(event) {
    event.preventDefault();
    const nienKhoa = document.getElementById('nienkhoa').value;
    const nienKhoaData =
        {
            "nienKhoa": nienKhoa
        }

    fetch(`http://localhost:8080/taoNKMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(nienKhoaData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thêm niên khóa mới thành công', 'success')
                layKhoaHocViChuyenNganhNienKhoa(event);
            } else {
                showToast(data.message, 'error')
            }
        })
}

document.getElementById('themNKForm').addEventListener('submit', taoNienKhoaMoi)

function taoNganhMoi(event) {
    event.preventDefault();
    let nganh = document.getElementById('nganh').value;
    if (/\d/.test(nganh)) {
        nganh = nganh.replace(/\d/g, '');
    }
    nganh = capitalizeFirstLetter(nganh);
    const CNData =
        {
            "chuyenNganh": nganh
        }

    fetch(`http://localhost:8080/taoNganhMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(CNData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thêm chuyên ngành mới thành công', 'success')
                layKhoaHocViChuyenNganhNienKhoa(event);
            } else {
                showToast(data.message, 'error')
            }
        })
}

document.getElementById('themNganhForm').addEventListener('submit', taoNganhMoi)

function taoHocViMoi(event) {
    event.preventDefault();
    let hocvi = document.getElementById('hocvi').value;
    if (/\d/.test(hocvi)) {
        hocvi = hocvi.replace(/\d/g, '');
    }
    hocvi = capitalizeFirstLetter(hocvi);
    const HVData =
        {
            "hocVi": hocvi
        }

    fetch(`http://localhost:8080/taoHocViMoi`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(HVData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thêm học vị mới thành công', 'success')
                layKhoaHocViChuyenNganhNienKhoa(event);
            } else {
                showToast(data.message, 'error')
            }
        })
}

document.getElementById('themHVForm').addEventListener('submit', taoHocViMoi)

function xoaItem(type, id) {
    const dashboard = document.getElementById('dashboard');
    const xoaPopup = document.createElement('div');
    let itemId = id;
    let message = '';
    switch (type) {
        case 'khoa':
            message = 'Bạn có chắc chắn muốn xóa khoa này?';
            break;
        case 'nienkhoa':
            message = 'Bạn có chắc chắn muốn xóa niên khóa này?';
            break;
        case 'hocvi':
            message = 'Bạn có chắc chắn muốn xóa học vị này?';
            break;
        case 'nganh':
            message = 'Bạn có chắc chắn muốn xóa chuyên ngành này?';
            break;
        default:
            message = 'Bạn có chắc chắn muốn xóa mục này?';
    }
    xoaPopup.id = 'xoaPopup';
    xoaPopup.innerHTML = ` <div class="deleteOverlay" id="overlay"></div> <div class="deletePopup" id="deletePopup"> <h2>${message}</h2> <br> <div class="delete-popup-buttons"> <button class="delete-popup-button cancel" onclick="ClosePopup()">Quay lại</button> <button class="delete-popup-button logout" onclick="DongYxoaItem('${type}', '${itemId}')">Xóa</button> </div> </div> `;
    dashboard.appendChild(xoaPopup);
}

function ClosePopup() {
    xoaPopup.remove();
}

function DongYxoaItem(type, itemId) {
    fetch(`http://localhost:8080/${type}/${itemId}`, {method: 'DELETE'}).then(response => {
        if (response.ok) {
            showToast('Xóa thành công', 'success');
            ClosePopup();
            layKhoaHocViChuyenNganhNienKhoa(new Event('fetch'));
        }
    })
}


