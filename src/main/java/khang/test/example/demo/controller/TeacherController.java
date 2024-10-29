package khang.test.example.demo.controller;

import khang.test.example.demo.Service.TeacherExcelUtility;
import khang.test.example.demo.Service.TeacherService;
import khang.test.example.demo.entity.Students;
import khang.test.example.demo.entity.Teachers;
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
public class TeacherController {
    @Autowired
    TeacherService teaService;
    @PostMapping("/teacher/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int code=1000;
        if (TeacherExcelUtility.hasExcelFormat(file)) {
            try {
                teaService.save(file);
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
    @GetMapping("/teacher-list")
    public ResponseEntity<?> getTeachers() {
        Map<String, Object> respTea = new LinkedHashMap<>();
        List<Teachers> teacherList = teaService.findAll();
        if (!teacherList.isEmpty()) {
            respTea.put("status", 1);
            respTea.put("data", teacherList);
            return new ResponseEntity<>(respTea, HttpStatus.OK);
        } else {
            respTea.clear();
            respTea.put("status", 0);
            respTea.put("message", "Data is not found");
            return new ResponseEntity<>(respTea, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/quanly-giangvien/search")
    public ResponseEntity<?> SearchMSSV(@Param("magv") String magv) {
        List<Teachers> teacherList = null;
        if (magv != null) {
            teacherList = teaService.SearchByMaGV(magv);
        }
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }
}
