package khang.test.example.demo.Service;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private void initStaticRepo() {
        svRepo = this.svRepo1;
        nienKhoaRepo = this.nienKhoaRepo1;
        khoaRepo = this.khoaRepo1;
        nganhRepo = this.nganhRepo1;
        tbaoRepo = this.tbaoRepo1;
    }

    public static File xuatFileExcel() {
        File file = null;
        try {
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

            Cell gioiTinh = row.createCell(2);
            gioiTinh.setCellValue("gioi tinh");

            Cell ngaySinh = row.createCell(3);
            ngaySinh.setCellValue("ngay sinh");

            Cell sdt = row.createCell(4);
            sdt.setCellValue("sdt");

            Cell email = row.createCell(5);
            email.setCellValue("email");

            Cell lop = row.createCell(6);
            lop.setCellValue("lop");

            Cell nienKhoa = row.createCell(7);
            nienKhoa.setCellValue("nien khoa");

            Cell chuyenNganh = row.createCell(8);
            chuyenNganh.setCellValue("chuyen nganh");

            Cell tenKhoa = row.createCell(9);
            tenKhoa.setCellValue("ten khoa");

            int rowNumber = 1;

            for (SinhVien sinhVien : sinhVienList) {
                row = sheet.createRow(rowNumber++);

                Cell stdMssv = row.createCell(0);
                stdMssv.setCellValue(sinhVien.getMSSV());

                Cell stdTensv = row.createCell(1);
                stdTensv.setCellValue(sinhVien.getTenSV());

                Cell stdGioitinh = row.createCell(2);
                stdGioitinh.setCellValue(sinhVien.getGioiTinh());

                Cell stdNgaySinh = row.createCell(3);
                stdNgaySinh.setCellValue(sinhVien.getNgaySinh());
                stdNgaySinh.setCellStyle(dateCellStyle);

                Cell stdSdt = row.createCell(4);
                stdSdt.setCellValue(sinhVien.getSDT());

                Cell stdEmail = row.createCell(5);
                stdEmail.setCellValue(sinhVien.getEmail());

                Cell stdLop = row.createCell(6);
                stdLop.setCellValue(sinhVien.getLop());

                Cell stdNienKhoa = row.createCell(7);
                stdNienKhoa.setCellValue(sinhVien.getNienKhoa());

                Cell stdChuyennganh = row.createCell(8);
                stdChuyennganh.setCellValue(sinhVien.getChuyenNganh());

                Cell stdTenKhoa = row.createCell(9);
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
            sheet.autoSizeColumn(9);

            final String fileName = "SinhVien";


            file = new File(fileName + ".xlsx");

            FileOutputStream outputStream = new FileOutputStream(file);

            workbook.write(outputStream);
            outputStream.close();
            workbook.close();


        } catch (Exception e) {

        }
        return file;
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
                            sinhvien.setGioiTinh(currentCell.getStringCellValue());
                            break;
                        case 3:
                            sinhvien.setNgaySinh(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 4:
                            sinhvien.setSDT(currentCell.getStringCellValue());
                            break;
                        case 5:
                            sinhvien.setEmail(currentCell.getStringCellValue());
                            break;
                        case 6:
                            sinhvien.setLop(currentCell.getStringCellValue());
                            break;
                        case 7:
                            sinhvien.setNienKhoa((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            sinhvien.setChuyenNganh(currentCell.getStringCellValue());
                            break;
                        case 9:
                            sinhvien.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                if (!nienKhoaRepo.existsByNienKhoa(sinhvien.getNienKhoa())) {
                    nienKhoa nienkhoa = new nienKhoa();
                    nienkhoa.setNienKhoa(sinhvien.getNienKhoa());
                    nienKhoaRepo.save(nienkhoa);
                }
                if (!khoaRepo.existsByTenKhoa(sinhvien.getTenKhoa())) {
                    Khoa khoa = new Khoa();
                    khoa.setTenKhoa(sinhvien.getTenKhoa());
                    khoaRepo.save(khoa);
                }
                if (!nganhRepo.existsByChuyenNganh(sinhvien.getChuyenNganh())) {
                    chuyenNganh nganh = new chuyenNganh();
                    nganh.setChuyenNganh(sinhvien.getChuyenNganh());
                    nganhRepo.save(nganh);
                }
                if (!svRepo.existsByMSSV(sinhvien.getMSSV())) {
                    if (svRepo.existsBySDT(sinhvien.getSDT()))
                        throw new AppException(ErrorCode.PhoneNumber_Existed);
                    Integer dodaiSDT = sinhvien.getSDT().length();
                    if (dodaiSDT<10 || dodaiSDT>11){
                        throw new AppException(ErrorCode.PhoneNumber_Invalid);
                    }
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
                    .nguoiThucHien("ADMIN")
                    .build();
            tbaoRepo.save(thongBao);
            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

