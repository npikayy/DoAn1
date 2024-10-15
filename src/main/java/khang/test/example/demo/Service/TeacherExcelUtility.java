package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Teachers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherExcelUtility {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "teacher";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Teachers> excelToStuList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Teachers> stuList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Teachers stu = new Teachers();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            stu.setMaGV((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            stu.setTenGV(currentCell.getStringCellValue());
                            break;
                        case 2:
                            stu.setTenNganh(currentCell.getStringCellValue());
                            break;
                        case 3:
                            stu.setTenKhoa(currentCell.getStringCellValue());
                            break;
                        case 4:
                            stu.setMaKhoa((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
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
