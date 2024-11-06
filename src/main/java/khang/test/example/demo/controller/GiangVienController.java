package khang.test.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.GiangVienExcelUtility;
import khang.test.example.demo.Service.GiangVienService;
import khang.test.example.demo.Service.SinhVienExcelUtility;
import khang.test.example.demo.entity.GiangVien;
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
public class GiangVienController {
    @Autowired
    GiangVienService gvService;
    @PutMapping("giangvien/{magv}")
    public apiResponse<GiangVien> updateGiangVien(@PathVariable String magv, @RequestBody GiangVien newGiangVien)
    {
        return apiResponse.<GiangVien>builder()
            .Code(1000)
            .result(gvService.capNhatGV(magv, newGiangVien))
            .build();
    }
    @Transactional
    @DeleteMapping("/giangvien/{magv}") public void xoaGiangVien(@PathVariable String magv)
    {
        gvService.xoaGiangVien(magv);
    }
    @PostMapping("/giangvien/upload")
    public apiResponse<List<GiangVien>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<GiangVien> gvList = null;
        if (GiangVienExcelUtility.hasExcelFormat(file)) {
            gvList = gvService.save(file);
        }

        return apiResponse.<List<GiangVien>>builder()
                .result(gvList)
                .message("Đã upload giảng viên mới lên hệ thống thành công")
                .build();
    }

    @GetMapping("/export/giangvien")
    public void xuatFileExcel(HttpServletResponse response) throws IOException {
        File file = GiangVienExcelUtility.xuatFileExcel();

        if(file != null){
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            response.addHeader("Content-Disposition","attachment; filename=" + file.getName());

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
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
    @PostMapping("/taoGVMoi")
    public apiResponse<GiangVien> taoGVmoi(@RequestBody GiangVien giangVien){
        return apiResponse.<GiangVien>builder()
                .Code(1000)
                .result(gvService.TaoGVmoi(giangVien))
                .build();
    }

    @GetMapping("/quanly-giangvien/search")
    public List<GiangVien> searchStudents(@RequestParam(required = false) String chuyenNganh,
                                         @RequestParam(required = false) String tenKhoa,
                                         @RequestParam(required = false) String hocvi,
                                         @RequestParam(required = false) String tenGV,
                                         @RequestParam(required = false) String magv
    )
    { return gvService.timGiangVien(chuyenNganh, tenKhoa, hocvi, tenGV, magv); }
}
