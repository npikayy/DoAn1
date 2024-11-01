package khang.test.example.demo.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import khang.test.example.demo.Service.SinhVienExcelUtility;
import khang.test.example.demo.Service.SinhVienService;
import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.response.apiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@Slf4j
public class SinhVienController {
    @Autowired
    SinhVienService stuService;
    @PostMapping("/student/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message ="";
        int code=1000;
        if (SinhVienExcelUtility.hasExcelFormat(file)) {
            try {
                stuService.save(file);
                code=0;
                message = "Tệp " + file.getOriginalFilename() + " đã được upload lên hệ thống thành công.";
            } catch (Exception exp) {
                log.warn(String.valueOf(exp));
                code=1000;
                message = "Tệp " + file.getOriginalFilename() + " upload không thành công! Vui lòng kiểm tra lại";
            }
        }
        return apiResponse.<String>builder()
                .Code(code)
                .result(message)
                .build();
    }
    @GetMapping("/sinhvien-list")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<SinhVien> studList = stuService.findAll();
        if (!studList.isEmpty()) {
            respStu.put("status", 1);
            respStu.put("data", studList);
            return new ResponseEntity<>(respStu, HttpStatus.OK);
        } else {
            respStu.clear();
            respStu.put("status", 0);
            respStu.put("message", "Data is not found");
            return new ResponseEntity<>(respStu, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/quanly-sinhvien/search")
    public List<SinhVien> searchStudents(@RequestParam(required = false) String chuyenNganh,
                                         @RequestParam(required = false) String tenKhoa,
                                         @RequestParam(required = false) Integer nienKhoa,
                                         @RequestParam(required = false) String tenSV,
                                         @RequestParam(required = false) String mssv
                                         )
    { return stuService.timSinhVien(chuyenNganh, tenKhoa, nienKhoa, tenSV, mssv); }

}
