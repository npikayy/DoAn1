package khang.test.example.demo.controller;

import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.DetaiService;
import khang.test.example.demo.Service.SinhVienExcelUtility;
import khang.test.example.demo.Service.SinhVienService;
import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.response.apiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class DetaiController {
    @Autowired
    DetaiService dtService;

    @PostMapping("/detai/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message ="";
        int code=1000;
        if (SinhVienExcelUtility.hasExcelFormat(file)) {
            try {
                dtService.save(file);
                code=0;
                message = "Upload tệp " + file.getOriginalFilename() + " lên hệ thống thành công.";
            } catch (Exception exp) {
                log.warn(String.valueOf(exp));
                code=1000;
                message = "Upload tệp " + file.getOriginalFilename() + " không thành công! Vui lòng kiểm tra lại";
            }
        }
        return apiResponse.<String>builder()
                .Code(code)
                .result(message)
                .build();
    }

}
