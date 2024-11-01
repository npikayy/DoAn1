package khang.test.example.demo.Service;

import jakarta.annotation.PostConstruct;
import khang.test.example.demo.entity.*;
import khang.test.example.demo.repository.GiangVienRepository;
import khang.test.example.demo.repository.HocViRepository;
import khang.test.example.demo.repository.KhoaRepository;
import khang.test.example.demo.repository.NganhRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class GiangVienExcelUtility {
    private static GiangVienRepository gvRepo;
    private static KhoaRepository khoaRepo;
    private static NganhRepository nganhRepo;
    private static HocViRepository hocviRepo;
    @Autowired
    private GiangVienRepository gvRepo1;
    @Autowired
    private KhoaRepository khoaRepo1;
    @Autowired
    private NganhRepository nganhRepo1;
    @Autowired
    private HocViRepository hocviRepo1;

    @PostConstruct
    private void initStaticRepo(){
        gvRepo = this.gvRepo1;
        khoaRepo = this.khoaRepo1;
        nganhRepo = this.nganhRepo1;
        hocviRepo = this.hocviRepo1;
    }
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "teacher";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<GiangVien> excelToTeacherList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<GiangVien> gvList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                GiangVien giangvien = new GiangVien();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            giangvien.setTenGV(currentCell.getStringCellValue());
                            break;
                        case 1:
                            giangvien.setNgaySinh(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 2:
                            giangvien.setEmail(currentCell.getStringCellValue());
                            break;
                        case 3:
                            giangvien.setHocvi(currentCell.getStringCellValue());
                            break;
                        case 4:
                            giangvien.setChuyenNganh(currentCell.getStringCellValue());
                            break;
                        case 5:
                            giangvien.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        case 6:
                            giangvien.setKinhNghiemGD(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                if (!khoaRepo.existsByTenKhoa(giangvien.getTenKhoa())){
                    Khoa khoa = new Khoa();
                    khoa.setTenKhoa(giangvien.getTenKhoa());
                    khoaRepo.save(khoa);
                }
                if (!nganhRepo.existsByChuyenNganh(giangvien.getChuyenNganh())){
                    chuyenNganh chuyenNganh = new chuyenNganh();
                    chuyenNganh.setChuyenNganh(giangvien.getChuyenNganh());
                    nganhRepo.save(chuyenNganh);
                }
                if (!hocviRepo.existsByHocVi(giangvien.getHocvi())){
                    hocVi hocvi = new hocVi();
                    hocvi.setHocVi(giangvien.getHocvi());
                    hocviRepo.save(hocvi);
                }
                boolean teacherExisted = gvRepo.existsByEmail(giangvien.getEmail());
                if (!teacherExisted)
                {
                    Accounts account = new Accounts();
                    account.setUsername(String.valueOf(giangvien.getEmail()));
                    account.setPassword("1");
                    account.setLoaiTK("giangvien");
                    giangvien.setThongTinTK(account);
                    gvList.add(giangvien);
                }
            }
            workbook.close();
            return gvList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
