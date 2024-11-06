package khang.test.example.demo.Service;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import jakarta.annotation.PostConstruct;
import khang.test.example.demo.entity.*;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.repository.admin_repository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

    public static File xuatFileExcel(){
        File file = null;
        try{
            List<SinhVien> sinhVienList = svRepo.findAll();


            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("sinhvien");

            CellStyle dateCellStyle = workbook.createCellStyle();
            short dateFormat = workbook.createDataFormat().getFormat("yyyy-MM-dd");
            dateCellStyle.setDataFormat(dateFormat);

            Row row = sheet.createRow(0);

            Cell mssv = row.createCell(0);
            mssv.setCellValue("mssv");

            Cell tensv = row.createCell(1);
            tensv.setCellValue("tensv");

            Cell ngaySinh = row.createCell(2);
            ngaySinh.setCellValue("ngay sinh");

            Cell sdt = row.createCell(3);
            sdt.setCellValue("sdt");

            Cell email = row.createCell(4);
            email.setCellValue("email");

            Cell lop = row.createCell(5);
            lop.setCellValue("lop");

            Cell nienKhoa = row.createCell(6);
            nienKhoa.setCellValue("nien khoa");

            Cell chuyenNganh = row.createCell(7);
            chuyenNganh.setCellValue("chuyen nganh");

            Cell tenKhoa = row.createCell(8);
            tenKhoa.setCellValue("ten khoa");

            int rowNumber = row.getRowNum();

            for(SinhVien sinhVien : sinhVienList){
                row = sheet.createRow(rowNumber++);

                Cell stdMssv = row.createCell(0);
                stdMssv.setCellValue(sinhVien.getMSSV());

                Cell stdTensv = row.createCell(1);
                stdTensv.setCellValue(sinhVien.getTenSV());

                Cell stdNgaySinh = row.createCell(2);
                stdNgaySinh.setCellValue(sinhVien.getNgaySinh());
                stdNgaySinh.setCellStyle(dateCellStyle);

                Cell stdSdt = row.createCell(3);
                stdSdt.setCellValue(sinhVien.getSDT());

                Cell stdEmail = row.createCell(4);
                stdEmail.setCellValue(sinhVien.getEmail());

                Cell stdLop = row.createCell(5);
                stdLop.setCellValue(sinhVien.getLop());

                Cell stdNienKhoa = row.createCell(6);
                stdNienKhoa.setCellValue(sinhVien.getNienKhoa());

                Cell stdChuyennganh = row.createCell(7);
                stdChuyennganh.setCellValue(sinhVien.getChuyenNganh());

                Cell stdTenKhoa = row.createCell(8);
                stdTenKhoa.setCellValue(sinhVien.getTenKhoa());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);


            final String fileName = "SinhVien";


            file = new File(fileName + ".xlsx");

            FileOutputStream outputStream = new FileOutputStream(file);

            workbook.write(outputStream);
            outputStream.close();
            workbook.close();


        }catch (Exception e){

        }
        return file;
    }
    public SinhVienExcelUtility(){
    }

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
            String duoiEmail = "@student.edu.vn";
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<SinhVien> stuList = new ArrayList<>();
            int rowNumber = 0;
            int cellIdx = 0;
            int SluongSV = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                SinhVien sinhvien = new SinhVien();
                cellIdx = 0;
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
                if (!nienKhoaRepo.existsByNienKhoa(sinhvien.getNienKhoa())) {
                    nienKhoa nienKhoa = new nienKhoa();
                    nienKhoa.setNienKhoa(sinhvien.getNienKhoa());
                    nienKhoaRepo.save(nienKhoa);
                }
                if (!khoaRepo.existsByTenKhoa(sinhvien.getTenKhoa())) {
                    Khoa khoa = new Khoa();
                    khoa.setTenKhoa(sinhvien.getTenKhoa());
                    khoaRepo.save(khoa);
                }
                if (!nganhRepo.existsByChuyenNganh(sinhvien.getChuyenNganh())) {
                    chuyenNganh chuyenNganh = new chuyenNganh();
                    chuyenNganh.setChuyenNganh(sinhvien.getChuyenNganh());
                    nganhRepo.save(chuyenNganh);
                }
                if (!svRepo.existsByMSSV(sinhvien.getMSSV())){
                    if (!sinhvien.getEmail().endsWith(duoiEmail))
                        throw new AppException(ErrorCode.INVALID_Student_Email);
                    if (svRepo.existsByEmail(sinhvien.getEmail()))
                        throw new AppException(ErrorCode.DUPLICATED_Email);

                    SluongSV++;
                    stuList.add(sinhvien);
                }
            }
            workbook.close();
            ThongBao thongBao = ThongBao.builder()
                    .noiDungTbao("Người dùng đã thêm " + SluongSV + " sinh viên mới bằng file excel")
                    .ngayThucHien(LocalDateTime.now())
                    .nguoiThucHien("Khang")
                    .build();
            tbaoRepo.save(thongBao);
            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

