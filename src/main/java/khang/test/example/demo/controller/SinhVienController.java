package khang.test.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.SinhVienExcelUtility;
import khang.test.example.demo.Service.SinhVienService;
import khang.test.example.demo.entity.SinhVien;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SinhVienController {
    @Autowired
    SinhVienService svService;

    @PutMapping("sinhvien/{mssv}")
    public apiResponse<SinhVien> updateStudent(@PathVariable String mssv, @RequestBody SinhVien newSinhVien) {
        return apiResponse.<SinhVien>builder()
                .Code(1000)
                .result(svService.capNhatSV(mssv, newSinhVien))
                .build();
    }

    @Transactional
    @DeleteMapping("/sinhvien/{mssv}")
    public void xoaSinhVien(@PathVariable String mssv) {
        svService.xoaSinhVien(mssv);
    }

    @Transactional
    @DeleteMapping("/xoasinhvien/")
    public void xoaAllSinhVien() {
        svService.xoaAllSinhVien();
    }

    @PostMapping("/student/upload")
    public apiResponse<List<SinhVien>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<SinhVien> svList = null;
        if (SinhVienExcelUtility.hasExcelFormat(file)) {
            svList = svService.save(file);
        }

        return apiResponse.<List<SinhVien>>builder()
                .result(svList)
                .message("Đã upload sinh viên mới lên hệ thống thành công")
                .build();
    }

    @GetMapping("/export/sinhvien")
    public void xuatFileExcel(HttpServletResponse response) throws IOException {
        File file = SinhVienExcelUtility.xuatFileExcel();

        if (file != null) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }

    @GetMapping("/sinhvien-list")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<SinhVien> studList = svService.findAll();
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

    @PostMapping("/taoSVMoi")
    public apiResponse<SinhVien> taoSVmoi(@RequestBody SinhVien sinhVien) {
        return apiResponse.<SinhVien>builder()
                .Code(1000)
                .result(svService.TaoSVmoi(sinhVien))
                .build();
    }

    @GetMapping("/quanly-sinhvien/search")
    public List<SinhVien> searchStudents(@RequestParam(required = false) String chuyenNganh,
                                         @RequestParam(required = false) String tenKhoa,
                                         @RequestParam(required = false) Integer nienKhoa,
                                         @RequestParam(required = false) String tenSV,
                                         @RequestParam(required = false) String mssv,
                                         @RequestParam(required = false) String gioiTinh
    ) {
        return svService.timSinhVien(chuyenNganh, tenKhoa, nienKhoa, tenSV, mssv, gioiTinh);
    }

}
