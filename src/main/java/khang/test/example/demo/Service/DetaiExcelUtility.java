package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Detai;
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

public class DetaiExcelUtility {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "detai";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Detai> excelToDetaiList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
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
//                        case 1:
//                            detai.setTendetai(currentCell.getLocalDateTimeCellValue().toLocalDate());
//                            break;
//                        case 2:
//                            detai.setEmail(currentCell.getStringCellValue());
//                            break;
//                        case 3:
//                            detai.setHocvi(currentCell.getStringCellValue());
//                            break;
//                        case 4:
//                            detai.setChuyenNganh(currentCell.getStringCellValue());
//                            break;
//                        case 5:
//                            detai.setTenKhoa(currentCell.getStringCellValue());
//                            break;
//                        case 6:
//                            detai.setKinhNghiemGD(currentCell.getStringCellValue());
//                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }

            }
            workbook.close();
            return dtList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
