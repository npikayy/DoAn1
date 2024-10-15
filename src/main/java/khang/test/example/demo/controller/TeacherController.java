package khang.test.example.demo.controller;

import khang.test.example.demo.Service.TeacherExcelUtility;
import khang.test.example.demo.Service.TeacherService;
import khang.test.example.demo.entity.Teachers;
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
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    TeacherService stuService;
    @PostMapping("/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int code=1000;
        if (TeacherExcelUtility.hasExcelFormat(file)) {
            try {
                stuService.save(file);
                code=0;
                message = "The Excel file is uploaded: " + file.getOriginalFilename();
            } catch (Exception exp) {
                log.warn(String.valueOf(exp));
                code=1000;
                message = "The Excel file is not upload: " + file.getOriginalFilename() + "!";
            }
        }
        return apiResponse.<String>builder()
                .Code(code)
                .result(message)
                .build();
    }
    @GetMapping("/teacher-list")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<Teachers> studList = stuService.findAll();
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
}
