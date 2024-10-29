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
    TeacherRepository teaRepo;
    public void save(MultipartFile file) {
        try {
            List<Teachers> teaList = TeacherExcelUtility.excelToTeacherList(file.getInputStream());
            teaRepo.saveAll(teaList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<Teachers> findAll() {
        return teaRepo.findAll();
    }
    public List<Teachers> SearchByMaGV(String magv){
        return teaRepo.searchByMaGV(magv);
    }
}
