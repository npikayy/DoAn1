package khang.test.example.demo.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import khang.test.example.demo.Service.StudentExcelUtility;
import khang.test.example.demo.Service.StudentService;
import khang.test.example.demo.entity.Students;
import khang.test.example.demo.response.apiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    StudentService stuService;
    @PostMapping("/upload")
    public apiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message ="";
        int code=1000;
        if (StudentExcelUtility.hasExcelFormat(file)) {
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
    @GetMapping("/student-list")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<Students> studList = stuService.findAll();
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
