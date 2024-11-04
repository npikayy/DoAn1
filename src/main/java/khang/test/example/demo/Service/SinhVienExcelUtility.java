package khang.test.example.demo.Service;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import jakarta.annotation.PostConstruct;
import khang.test.example.demo.entity.*;
import khang.test.example.demo.repository.admin_repository.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SinhVienExcelUtility {
    private static SinhVienRepository svRepo;
    private static NienKhoaRepository nienKhoaRepo;
    private static KhoaRepository khoaRepo;
    private static NganhRepository nganhRepo;
    private static ThongBaoRepository tbaoRepo;

    @Autowired
    private SinhVienRepository svRepo1;
    @Autowired
    private NienKhoaRepository nienKhoaRepo1;
    @Autowired
    private KhoaRepository khoaRepo1;
    @Autowired
    private NganhRepository nganhRepo1;
    @Autowired
    private ThongBaoRepository tbaoRepo1;
    @PostConstruct
    private void initStaticRepo(){
        svRepo = this.svRepo1;
        nienKhoaRepo = this.nienKhoaRepo1;
        khoaRepo = this.khoaRepo1;
        nganhRepo = this.nganhRepo1;
        tbaoRepo = this.tbaoRepo1;
    }

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "student";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<SinhVien> excelToStuList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<SinhVien> stuList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                SinhVien sinhvien = new SinhVien();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            sinhvien.setMSSV(currentCell.getStringCellValue());
                            break;
                        case 1:
                            sinhvien.setTenSV(currentCell.getStringCellValue());
                            break;
                        case 2:
                            sinhvien.setNgaySinh(currentCell.getLocalDateTimeCellValue().toLocalDate());

                            break;
                        case 3:
                            sinhvien.setSDT((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            sinhvien.setEmail(currentCell.getStringCellValue());
                            break;
                        case 5:
                            sinhvien.setLop(currentCell.getStringCellValue());
                            break;
                        case 6:
                            sinhvien.setNienKhoa((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            sinhvien.setChuyenNganh(currentCell.getStringCellValue());
                            break;
                        case 8:
                            sinhvien.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                if (!nienKhoaRepo.existsByNienKhoa(sinhvien.getNienKhoa())){
                    nienKhoa nienKhoa = new nienKhoa();
                    nienKhoa.setNienKhoa(sinhvien.getNienKhoa());
                    nienKhoaRepo.save(nienKhoa);
                }
                if (!khoaRepo.existsByTenKhoa(sinhvien.getTenKhoa())){
                    Khoa khoa = new Khoa();
                    khoa.setTenKhoa(sinhvien.getTenKhoa());
                    khoaRepo.save(khoa);
                }
                if (!nganhRepo.existsByChuyenNganh(sinhvien.getChuyenNganh())){
                    chuyenNganh chuyenNganh = new chuyenNganh();
                    chuyenNganh.setChuyenNganh(sinhvien.getChuyenNganh());
                    nganhRepo.save(chuyenNganh);
                }
                if (!svRepo.existsByMSSV(sinhvien.getMSSV()))
                {
                    stuList.add(sinhvien);
                }
            }
            workbook.close();
            ThongBao thongBao = ThongBao.builder()
                    .noiDungTbao("Người dùng đã upload thêm sinh viên mới")
                    .ngayThucHien(LocalDate.now())
                    .nguoiThucHien("Khang")
                    .build();
            tbaoRepo.save(thongBao);
            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
