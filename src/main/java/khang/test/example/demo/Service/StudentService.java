package khang.test.example.demo.Service;
import java.io.IOException;
import java.util.List;

import khang.test.example.demo.entity.Students;
import khang.test.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class StudentService {
    @Autowired
    StudentRepository stuRepo;
    public void save(MultipartFile file) {
        try {
            List<Students> stuList = StudentExcelUtility.excelToStuList(file.getInputStream());
            stuRepo.saveAll(stuList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<Students> findAll() {
        return stuRepo.findAll();
    }

    public List<Students> SearchByMSSV(String mssv){
        return stuRepo.searchByMssv(mssv);
    }

}
