package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Teachers;
import khang.test.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository stuRepo;
    public void save(MultipartFile file) {
        try {
            List<Teachers> stuList = TeacherExcelUtility.excelToStuList(file.getInputStream());
            stuRepo.saveAll(stuList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<Teachers> findAll() {
        return stuRepo.findAll();
    }
}
