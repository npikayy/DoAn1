package khang.test.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.DetaiExcelUtility;
import khang.test.example.demo.Service.DetaiService;
import khang.test.example.demo.Service.SinhVienExcelUtility;
import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.response.apiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class DetaiController {
    @Autowired
    DetaiService dtService;

    @PutMapping("detai/{madetai}")
    public apiResponse<Detai> updateDetai(@PathVariable String madetai, @RequestBody Detai newDetai) {
        return apiResponse.<Detai>builder()
                .Code(1000)
                .result(dtService.capNhatDT(madetai, newDetai))
                .build();
    }

    @Transactional
    @DeleteMapping("/detai/{madetai}")
    public void xoaDeTai(@PathVariable String madetai) {
        dtService.xoaDeTai(madetai);
    }

    @Transactional
    @DeleteMapping("/xoadetai/")
    public void xoaTatCaDeTai() {
        dtService.XoaTatcaDetai();
    }

    @GetMapping("/export/detai")
    public void xuatFileExcel(HttpServletResponse response) throws IOException {
        File file = DetaiExcelUtility.xuatFileExcel();

        if (file != null) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }

    @PostMapping("/detai/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int code = 1000;
        if (SinhVienExcelUtility.hasExcelFormat(file)) {
            try {
                dtService.save(file);
                code = 0;
                message = "Upload tệp " + file.getOriginalFilename() + " lên hệ thống thành công.";
            } catch (Exception exp) {
                log.warn(String.valueOf(exp));
                code = 1000;
                message = "Upload tệp " + file.getOriginalFilename() + " không thành công! " +String.valueOf(exp.getMessage()) ;
            }
        }
        return apiResponse.<String>builder()
                .Code(code)
                .result(message)
                .build();
    }

    @GetMapping("/quanly-detai/search")
    public List<Detai> searchStudents(@RequestParam(required = false) String tenKhoa,
                                      @RequestParam(required = false) String tinhTrang,
                                      @RequestParam(required = false) String maGV,
                                      @RequestParam(required = false) String madetai,
                                      @RequestParam(required = false) LocalDate ngayBatdau,
                                      @RequestParam(required = false) LocalDate ngayKetthuc

    ) {
        return dtService.timDeTai(tenKhoa, tinhTrang, maGV, madetai, ngayBatdau, ngayKetthuc);
    }

    @GetMapping("/detai-list")
    public ResponseEntity<?> getDetai() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<Detai> dtList = dtService.findAll();
        if (!dtList.isEmpty()) {
            respStu.put("status", 1);
            respStu.put("data", dtList);
            return new ResponseEntity<>(respStu, HttpStatus.OK);
        } else {
            respStu.clear();
            respStu.put("status", 0);
            respStu.put("message", "Data is not found");
            return new ResponseEntity<>(respStu, HttpStatus.NOT_FOUND);
        }
    }


}
