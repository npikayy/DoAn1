package khang.test.example.demo.Service;

import jakarta.annotation.PostConstruct;
import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.entity.Khoa;
import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.repository.admin_repository.DeTaiRepository;
import khang.test.example.demo.repository.admin_repository.KhoaRepository;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
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
public class DetaiExcelUtility {

    private static DeTaiRepository dtRepo;
    private static ThongBaoRepository tbaoRepo;

    private static KhoaRepository khoaRepo;

    @Autowired
    private DeTaiRepository dtRepo1;
    @Autowired
    private ThongBaoRepository tbaoRepo1;
    @Autowired
    private KhoaRepository khoaRepo1;

    @PostConstruct
    private void initStaticRepo() {
        dtRepo = this.dtRepo1;
        tbaoRepo = this.tbaoRepo1;
        khoaRepo = this.khoaRepo1;
    }

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    //    static String SHEET = "detai";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static File xuatFileExcel() {
        File file = null;
        try {
            List<Detai> DetaiList = dtRepo.findAll();


            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("detai");

            CellStyle dateCellStyle = workbook.createCellStyle();
            short dateFormat = workbook.createDataFormat().getFormat("yyyy-MM-dd");
            dateCellStyle.setDataFormat(dateFormat);

            Row row = sheet.createRow(0);

            Cell header1 = row.createCell(0);
            header1.setCellValue("ma de tai");

            Cell header2 = row.createCell(1);
            header2.setCellValue("ten de tai");

            Cell header3 = row.createCell(2);
            header3.setCellValue("ma giang vien");

            Cell header4 = row.createCell(3);
            header4.setCellValue("so luong sinh vien");

            Cell header5 = row.createCell(4);
            header5.setCellValue("khoa");

            Cell header6 = row.createCell(5);
            header6.setCellValue("ngay tao de tai");

            Cell header7 = row.createCell(6);
            header7.setCellValue("ngay bat dau de tai");

            Cell header8 = row.createCell(7);
            header8.setCellValue("ngay ket thuc de tai");

            Cell header9 = row.createCell(8);
            header9.setCellValue("tinh trang");
            int rowNumber = row.getRowNum();

            for (Detai detai : DetaiList) {
                row = sheet.createRow(rowNumber++);

                Cell std1 = row.createCell(0);
                std1.setCellValue(detai.getMadetai());

                Cell std2 = row.createCell(1);
                std2.setCellValue(detai.getTendetai());

                Cell std3 = row.createCell(2);
                std3.setCellValue(detai.getMaGiangvien());


                Cell std4 = row.createCell(3);
                std4.setCellValue(detai.getSoLuongThanhVien());

                Cell std5 = row.createCell(4);
                std5.setCellValue(detai.getTenKhoa());

                Cell std6 = row.createCell(5);
                std6.setCellValue(detai.getNgayTaoDetai());
                std6.setCellStyle(dateCellStyle);

                Cell std7 = row.createCell(6);
                std7.setCellValue(detai.getNgayBatdau());
                std7.setCellStyle(dateCellStyle);

                Cell std8 = row.createCell(7);
                std8.setCellValue(detai.getNgayKetthuc());
                std8.setCellStyle(dateCellStyle);

                Cell std9 = row.createCell(8);
                std9.setCellValue(detai.getTinhtrang());
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

            final String fileName = "DeTai";


            file = new File(fileName + ".xlsx");

            FileOutputStream outputStream = new FileOutputStream(file);

            workbook.write(outputStream);
            outputStream.close();
            workbook.close();


        } catch (Exception e) {

        }
        return file;
    }

    public static List<Detai> excelToDetaiList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Detai> dtList = new ArrayList<>();
            int rowNumber = 0;
            int SluongDT = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Detai detai = new Detai();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            detai.setMadetai(currentCell.getStringCellValue());
                            break;
                        case 1:
                            detai.setTendetai(currentCell.getStringCellValue());
                            break;
                        case 2:
                            detai.setMaGiangvien(currentCell.getStringCellValue());
                            break;
                        case 3:
                            detai.setSoLuongThanhVien((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            detai.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        case 5:
                            detai.setNgayTaoDetai(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 6:
                            detai.setNgayBatdau(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 7:
                            detai.setNgayKetthuc(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 8:
                            detai.setTinhtrang(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                if (!dtRepo.existsByMadetai(detai.getMadetai())) {
                    if (!dtRepo.existsByTenKhoa(detai.getTenKhoa())) {
                        Khoa khoa = new Khoa();
                        khoa.setTenKhoa(detai.getTenKhoa());
                        khoaRepo.save(khoa);
                    }
                    if (detai.getNgayTaoDetai().isAfter(detai.getNgayBatdau()) || detai.getNgayTaoDetai().isAfter(detai.getNgayKetthuc())) {
                        throw new AppException(ErrorCode.Invalid_CreateDay);
                    }
                    if (detai.getNgayBatdau().isAfter(detai.getNgayKetthuc())) {
                        throw new AppException(ErrorCode.InvalidDay);
                    }
                    if ("Chưa hoàn thành".equals(detai.getTinhtrang())) {
                        detai.setNgayKetthuc(null);
                    }
                    if (detai.getSoLuongThanhVien() <= 0) {
                        throw new AppException(ErrorCode.Invalid_Number);
                    }
                    SluongDT++;
                    dtList.add(detai);
                }
            }
            workbook.close();
            ThongBao thongBao = ThongBao.builder()
                    .noiDungTbao("Người dùng đã upload thêm " + SluongDT + " đề tài ")
                    .ngayThucHien(LocalDateTime.now())
                    .nguoiThucHien("ADMIN")
                    .build();
            tbaoRepo.save(thongBao);
            workbook.close();
            return dtList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
