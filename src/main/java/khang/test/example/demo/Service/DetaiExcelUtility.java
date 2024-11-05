package khang.test.example.demo.Service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.repository.admin_repository.DeTaiRepository;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class DetaiExcelUtility {

    private static DeTaiRepository dtRepo;
    private static ThongBaoRepository tbaoRepo;

    @Autowired
    private DeTaiRepository dtRepo1;
    @Autowired
    private ThongBaoRepository tbaoRepo1;

    @PostConstruct
    private void initStaticRepo(){
        dtRepo = this.dtRepo1;
        tbaoRepo = this.tbaoRepo1;
    }

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String SHEET = "detai";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Detai> excelToDetaiList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Detai> dtList = new ArrayList<>();
            int rowNumber = 0;
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
                if (!dtRepo.existsByMadetai(detai.getMadetai()))
                {
                    dtList.add(detai);
                }
            }
            workbook.close();
            ThongBao thongBao = ThongBao.builder()
                    .noiDungTbao("Người dùng đã upload thêm đề tài ")
                    .ngayThucHien(LocalDate.now())
                    .nguoiThucHien("Khang")
                    .build();
            tbaoRepo.save(thongBao);
            workbook.close();
            return dtList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
