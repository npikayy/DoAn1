package khang.test.example.demo.controller;

import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.GiangVienExcelUtility;
import khang.test.example.demo.Service.GiangVienService;
import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.response.apiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GiangVienController {
    @Autowired
    GiangVienService gvService;
    @PutMapping("giangvien/{magv}")
    public void updateGiangVien(@PathVariable String magv, @RequestBody GiangVien newGiangVien)
    {
        gvService.capNhatGV(magv, newGiangVien);
    }
    @Transactional
    @DeleteMapping("/giangvien/{magv}") public void xoaGiangVien(@PathVariable String magv)
    {
        gvService.xoaGiangVien(magv);
    }
    @PostMapping("/giangvien/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int code=1000;
        if (GiangVienExcelUtility.hasExcelFormat(file)) {
            try {
                gvService.save(file);
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
    @GetMapping("/giangVien-list")
    public ResponseEntity<?> laygiangVien() {
        Map<String, Object> respTea = new LinkedHashMap<>();
        List<GiangVien> giangVienList = gvService.findAll();
        if (!giangVienList.isEmpty()) {
            respTea.put("status", 1);
            respTea.put("data", giangVienList);
            return new ResponseEntity<>(respTea, HttpStatus.OK);
        } else {
            respTea.clear();
            respTea.put("status", 0);
            respTea.put("message", "Data is not found");
            return new ResponseEntity<>(respTea, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/quanly-giangvien/search")
    public List<GiangVien> searchStudents(@RequestParam(required = false) String chuyenNganh,
                                         @RequestParam(required = false) String tenKhoa,
                                         @RequestParam(required = false) String hocvi,
                                         @RequestParam(required = false) String tenGV,
                                         @RequestParam(required = false) String email
    )
    { return gvService.timGiangVien(chuyenNganh, tenKhoa, hocvi, tenGV, email); }
}
