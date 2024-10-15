package khang.test.example.demo.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import khang.test.example.demo.entity.Students;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
public class StudentExcelUtility {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "student";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Students> excelToStuList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Students> stuList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Students stu = new Students();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            stu.setMSSV((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            stu.setStudentName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            stu.setMobileNo((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            stu.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            stu.setLop(currentCell.getStringCellValue());
                            break;
                        case 5:
                            stu.setNienKhoa((int) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            stu.setMaNhom((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            stu.setNgaySinh(currentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                stuList.add(stu);
            }
            workbook.close();
            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
