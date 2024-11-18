async function layKhoa() {
    const selectKhoa = document.getElementById('khoa')
    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const {data1} = await responseAPI1.json();
    data1.forEach(data1 => {
        const optionItem = document.createElement('option')
        optionItem.value = `${data1.tenKhoa}`
        optionItem.innerText = `${data1.tenKhoa}`
        selectKhoa.appendChild(optionItem)

    })
}

layKhoa();

async function themKhoa() {
    const selectKhoa = document.getElementById('tenKhoa')
    const responseAPI1 = await fetch('http://localhost:8080/khoa-list');
    const {data1} = await responseAPI1.json();
    data1.forEach(data1 => {
        const optionItem = document.createElement('option')
        optionItem.value = `${data1.tenKhoa}`
        optionItem.innerText = `${data1.tenKhoa}`
        selectKhoa.appendChild(optionItem)

    })
}

themKhoa()

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

function laydulieuDT(madetai) {
    event.preventDefault();
    showUpdateInput()

    fetch(`http://localhost:8080/quanly-detai/search?madetai=${madetai}`).then(response => response.json())
        .then(data => {
            data.forEach(data => {
                addDatatoInput(data);
            })
        })

}
function changeTinhtrang(){
    let tinhtrang = document.getElementById('tinhtrang')
    tinhtrang.value = "Đã hoàn thành"
    tinhtrang.disabled = true;
}
function changeTinhtrangtoDefault(){

}
async function timDTBangDieuKien(event) {
    event.preventDefault()
    let khoa = document.getElementById('khoa').value
    let tinhtrang = document.getElementById('tinhtrang').value
    let start = document.getElementById('startDay').value
    let end = document.getElementById('endDay').value
    let search = document.getElementById('search').value
    let magvOrmadetai = document.getElementById('magvOrmadetai').value

    let url = 'http://localhost:8080/quanly-detai/search?';
    changeTinhtrangtoDefault()
    if (start !== null) {
        url += `ngayBatdau=${start}&`;
    }
    if (end) {
        url += `ngayKetthuc=${end}&`;
        // changeTinhtrang();
        let tinhtrang = document.getElementById('tinhtrang')
        tinhtrang.value = "Đã hoàn thành"
        tinhtrang.disabled = true;
    }
    else{
        let tinhtrang = document.getElementById('tinhtrang')
        tinhtrang.value = "none"
        tinhtrang.disabled = false;
    }

    if (khoa !== 'none') {
        url += `tenKhoa=${khoa}&`;
    }


    if (tinhtrang !== 'none') {
        url += `tinhTrang=${tinhtrang}&`;
    }

    if (search !== '') {
        if (magvOrmadetai === 'maGV') {
            url += `maGV=${search}&`;
        } else {
            url += `madetai=${search}&`;
        }
    }
    url = url.slice(0, -1);

    if (url === 'http://localhost:8080/quanly-detai/search') {
        const responseAPI = await fetch('http://localhost:8080/detai-list');
        const {data} = await responseAPI.json();
        addTabletitle();
        data.forEach(data => {
            addDatatoTable(data);
        });
    } else {
        fetch(url).then(response => response.json())
            .then(data => {
                addTabletitle();
                data.forEach(data => {
                    addDatatoTable(data);
                });
            })
            .catch(error => console.error('Error:', error));
    }
}

function addTabletitle() {
    const list = document.getElementById('userList')
    list.innerHTML =
        `
       <th>Mã đề tài</th>
       <th>Tên đề tài</th>
       <th>Mã giảng viên</th>
       <th>Số lượng sinh viên</th>
       <th>Tên khoa</th>
       <th>Ngày tạo đề tài</th>
       <th>Ngày bắt đầu đề tài</th>
       <th>Ngày kết thúc đề tài</th>
       <th>Tình trạng</th>
       <th>
       <button id="xoaBtn" onclick="xoaAllDT()">Xóa tất cả</button>
        </th>
   `

}

function addDatatoInput(data) {
    const madetai = document.getElementById('madetai');
    const tenDT = document.getElementById('tenDT');
    const maGV = document.getElementById('maGV');
    const Sluong = document.getElementById('SLuong');
    const tenKhoa = document.getElementById('tenKhoa');
    const ngayTaoDT = document.getElementById('ngayTaoDT');
    const ngayBD = document.getElementById('ngayBatDau');
    const ngayKT = document.getElementById('ngayKetThuc');
    const tinhtrang = document.getElementById('trangThai');

    madetai.value = `${data.madetai}`;
    tenDT.value = `${data.tendetai}`;
    maGV.value = `${data.maGiangvien}`;
    Sluong.value = `${data.soLuongThanhVien}`;
    tenKhoa.value = `${data.tenKhoa}`;
    ngayTaoDT.value = `${data.ngayTaoDetai}`;
    ngayBD.value = `${data.ngayBatdau}`;
    ngayKT.value = `${data.ngayKetthuc}`;
    tinhtrang.value = `${data.tinhtrang}`
}

function addDatatoTable(data) {
    const list = document.getElementById('userList')
    const divItem2 = document.createElement('tr')
    let madetai = data.madetai;
    divItem2.innerHTML = `
       <td>${data.madetai}</td>
        <td>${data.tendetai}</td>
        <td>${data.maGiangvien}</td>
        <td>${data.soLuongThanhVien}</td>
        <td>${data.tenKhoa}</td>
        <td>${data.ngayTaoDetai}</td>
        <td>${data.ngayBatdau}</td>
        <td>${data.ngayKetthuc}</td>
        <td>${data.tinhtrang}</td>
        <td>
            <div>
                <button id="capNhatBtn" onclick="laydulieuDT('${madetai}')">Sửa</button>
                <button id="xoaBtn" onclick="xoaDT('${madetai}')">Xóa</button>
            </div>
        </td>
       `;
    list.appendChild(divItem2)
}

function xoaDT(madetai) {
    const dashboard = document.getElementById('dashboard')
    const xoaPopup = document.createElement('div');
    let detai = madetai
    xoaPopup.id = 'xoaPopup';
    xoaPopup.innerHTML = `
     <div class="deleteOverlay" id="overlay"></div>
     <div class="deletePopup" id="deletePopup">
         <h2>Bạn có chắc chắn muốn xóa đề tài này?</h2>
         <br>
             <div class="delete-popup-buttons">
                 <button class="delete-popup-button cancel" onclick="ClosePopup()">Quay lại</button>
                 <button class="delete-popup-button logout" onclick="DongYxoaDT('${detai}')">Xóa</button>
             </div>
    </div>
    `
    dashboard.appendChild(xoaPopup)
}

function ClosePopup() {
    xoaPopup.remove();
}

function DongYxoaDT(madetai) {
    fetch(`http://localhost:8080/detai/${madetai}`, {method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                showToast('Xóa đề tài thành công', 'success');
                timDTBangDieuKien(new Event('fetch'));
                ClosePopup();
            }
        })
}

function xoaAllDT() {
    const dashboard = document.getElementById('dashboard')
    const xoaPopup = document.createElement('div');
    xoaPopup.id = 'xoaPopup';
    xoaPopup.innerHTML = `
     <div class="deleteOverlay" id="overlay"></div>
     <div class="deletePopup" id="deletePopup">
         <h2>Bạn có chắc chắn muốn xóa tất cả đề tài?</h2>
         <br>
             <div class="delete-popup-buttons">
                 <button class="delete-popup-button cancel" onclick="ClosePopup()">Quay lại</button>
                 <button class="delete-popup-button logout" onclick="DongYxoaAllDT()">Xóa</button>
             </div>
    </div>
    `
    dashboard.appendChild(xoaPopup)
}

function DongYxoaAllDT() {
    fetch(`http://localhost:8080/xoadetai/`, {method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                showToast('Xóa tất cả đề tài thành công', 'success');
                timDTBangDieuKien(new Event('fetch'));
                ClosePopup();
            }
        })
}


function updateDT(event) {
    event.preventDefault();
    const madetai = document.getElementById('madetai').value;
    const tenDT = document.getElementById('tenDT').value;
    const maGV = document.getElementById('maGV').value;
    const Sluong = document.getElementById('SLuong').value;
    const tenKhoa = document.getElementById('tenKhoa').value;
    const ngayTaoDT = document.getElementById('ngayTaoDT').value;
    const ngayBD = document.getElementById('ngayBatDau').value;
    const ngayKT = document.getElementById('ngayKetThuc').value;
    const tinhtrang = document.getElementById('trangThai').value;

    const DTData =
        {
            "madetai": madetai,
            "tendetai": tenDT,
            "maGiangvien": maGV,
            "soLuongThanhVien": Sluong,
            "tenKhoa": tenKhoa,
            "ngayTaoDetai": ngayTaoDT,
            "ngayBatdau": ngayBD,
            "ngayKetthuc": ngayKT,
            "tinhtrang": tinhtrang
        }

    fetch(`http://localhost:8080/detai/${madetai}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(DTData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 1000) {
                showToast('Thông tin đề tài đã được cập nhật thành công', 'success')
                hideUpdateInput();
                timDTBangDieuKien(event);
            } else {
                showToast(data.message, 'error')
            }
        })
};
document.getElementById('updateDTForm').addEventListener('submit', updateDT)


document.getElementById('print-button').addEventListener('click', () => {
    // Create a new window for printing
    const printWindow = window.open('', '_blank');

    // Get the table content
    const table = document.querySelector('table').cloneNode(true);

    const rows = table.rows;
    for (let i = 0; i < rows.length; i++) {
        rows[i].deleteCell(-1);
    }

    // Create print-friendly styles
    const printStyles = `
        <style>
            body { font-family: Arial, sans-serif; }
            table { width: 100%; border-collapse: collapse; margin: 20px 0; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            @media print {
                @page { margin: 1cm; }
                .no-print { display: none; }
            }
        </style>
    `;

    // Set up the print window content
    printWindow.document.write(`
        <!DOCTYPE html>
        <html>
        <head>
            <title>Danh Sách Đề Tài</title>
            ${printStyles}
        </head>
        <body>
            <h1>Danh Sách Đề Tài</h1>
            ${table.outerHTML}
        </body>
        </html>
    `);

    // Wait for content to load then print
    printWindow.document.close();
    printWindow.onload = function () {
        printWindow.print();
        printWindow.onafterprint = function () {
            printWindow.close();
        };
    };
});

// Add required CSS animations
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    
    @keyframes slideOut {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);

