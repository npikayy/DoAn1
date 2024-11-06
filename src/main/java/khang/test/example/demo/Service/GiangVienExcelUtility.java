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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class GiangVienExcelUtility {
    private static GiangVienRepository gvRepo;
    private static KhoaRepository khoaRepo;
    private static NganhRepository nganhRepo;
    private static HocViRepository hocviRepo;
    private static ThongBaoRepository tbaoRepo;

    @Autowired
    private GiangVienRepository gvRepo1;
    @Autowired
    private KhoaRepository khoaRepo1;
    @Autowired
    private NganhRepository nganhRepo1;
    @Autowired
    private HocViRepository hocviRepo1;
    @Autowired
    private ThongBaoRepository tbaoRepo1;

    @PostConstruct
    private void initStaticRepo(){
        gvRepo = this.gvRepo1;
        khoaRepo = this.khoaRepo1;
        nganhRepo = this.nganhRepo1;
        hocviRepo = this.hocviRepo1;
        tbaoRepo = this.tbaoRepo1;
    }
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static File xuatFileExcel(){
        File file = null;
        try{
            List<GiangVien> giangVienList = gvRepo.findAll();


            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("giangvien");

            CellStyle dateCellStyle = workbook.createCellStyle();
            short dateFormat = workbook.createDataFormat().getFormat("yyyy-MM-dd");
            dateCellStyle.setDataFormat(dateFormat);

            Row row = sheet.createRow(0);

            Cell magv = row.createCell(0);
            magv.setCellValue("ma gv");

            Cell tengv = row.createCell(1);
            tengv.setCellValue("tengv");

            Cell ngaySinh = row.createCell(2);
            ngaySinh.setCellValue("ngay sinh");
            
            Cell email = row.createCell(3);
            email.setCellValue("email");

            Cell hocvi = row.createCell(4);
            hocvi.setCellValue("hocvi");

            Cell chuyenNganh = row.createCell(5);
            chuyenNganh.setCellValue("chuyen nganh");

            Cell tenKhoa = row.createCell(6);
            tenKhoa.setCellValue("ten khoa");

            Cell kinhNghiem = row.createCell(7);
            kinhNghiem.setCellValue("kinh nghiem gd");

            int rowNumber = row.getRowNum();

            for(GiangVien giangVien : giangVienList){
                row = sheet.createRow(rowNumber++);

                Cell stdmagv = row.createCell(0);
                stdmagv.setCellValue(giangVien.getMaGV());

                Cell stdtengv = row.createCell(1);
                stdtengv.setCellValue(giangVien.getTenGV());

                Cell stdngaySinh = row.createCell(2);
                stdngaySinh.setCellValue(giangVien.getNgaySinh());
                stdngaySinh.setCellStyle(dateCellStyle);

                Cell stdemail = row.createCell(3);
                stdemail.setCellValue(giangVien.getEmail());

                Cell stdhocvi = row.createCell(4);
                stdhocvi.setCellValue(giangVien.getHocvi());

                Cell stdchuyenNganh = row.createCell(5);
                stdchuyenNganh.setCellValue(giangVien.getChuyenNganh());

                Cell stdtenKhoa = row.createCell(6);
                stdtenKhoa.setCellValue(giangVien.getTenKhoa());

                Cell stdkinhNghiem = row.createCell(7);
                stdkinhNghiem.setCellValue(giangVien.getKinhNghiemGD());
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


            final String fileName = "GiangVien";


            file = new File(fileName + ".xlsx");

            FileOutputStream outputStream = new FileOutputStream(file);

            workbook.write(outputStream);
            outputStream.close();
            workbook.close();


        }catch (Exception e){

        }
        return file;
    }
    public static List<GiangVien> excelToTeacherList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            String duoiEmail = "@teacher.edu.vn";
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<GiangVien> gvList = new ArrayList<>();
            int rowNumber = 0;
            int SluongGV = 0;
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
                            giangvien.setMaGV(currentCell.getStringCellValue());
                            break;
                        case 1:
                            giangvien.setTenGV(currentCell.getStringCellValue());
                            break;
                        case 2:
                            giangvien.setNgaySinh(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 3:
                            giangvien.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            giangvien.setHocvi(currentCell.getStringCellValue());
                            break;
                        case 5:
                            giangvien.setChuyenNganh(currentCell.getStringCellValue());
                            break;
                        case 6:
                            giangvien.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        case 7:
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
                if (!gvRepo.existsByMaGV(giangvien.getMaGV())) {
                    if (!giangvien.getEmail().endsWith(duoiEmail))
                        throw new AppException(ErrorCode.INVALID_Teacher_Email);
                    if (gvRepo.existsByEmail(giangvien.getEmail()))
                        throw new AppException(ErrorCode.DUPLICATED_Email);

                    SluongGV++;
                    gvList.add(giangvien);
                }
            }
            workbook.close();
            ThongBao thongBao = ThongBao.builder()
                    .noiDungTbao("Người dùng đã thêm " + SluongGV +" giảng viên mới bằng file excel ")
                    .ngayThucHien(LocalDateTime.now())
                    .nguoiThucHien("Khang")
                    .build();
            tbaoRepo.save(thongBao);
            return gvList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
