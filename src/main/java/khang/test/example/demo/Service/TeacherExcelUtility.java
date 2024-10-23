package khang.test.example.demo.Service;

import jakarta.annotation.PostConstruct;
import khang.test.example.demo.entity.Accounts;
import khang.test.example.demo.entity.Teachers;
import khang.test.example.demo.repository.TeacherRepository;
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
public class TeacherExcelUtility {
    private static TeacherRepository teaRepo;
    @Autowired
    private TeacherRepository teaRepo1;

    @PostConstruct
    private void initStaticRepo(){
        teaRepo = this.teaRepo1;
    }
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "teacher";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Teachers> excelToTeacherList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Teachers> teaList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Teachers teacher = new Teachers();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            teacher.setMaGV((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            teacher.setTenGV(currentCell.getStringCellValue());
                            break;
                        case 2:
                            teacher.setEmail(currentCell.getStringCellValue());
                            break;
                        case 3:
                            teacher.setTenNganh(currentCell.getStringCellValue());
                            break;
                        case 4:
                            teacher.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        case 5:
                            teacher.setMaKhoa((int) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            teacher.setNgaySinh(currentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                boolean teacherExisted = teaRepo.existsByMaGV(teacher.getMaGV());
                if (!teacherExisted)
                {
                    Accounts account = new Accounts();
                    account.setUsername(String.valueOf(teacher.getMaGV()));
                    account.setPassword("1");
                    account.setLoaiTK("teacher");
                    teacher.setThongTinTK(account);
                    teaList.add(teacher);
                }
            }
            workbook.close();
            return teaList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
