package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.repository.admin_repository.DeTaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DetaiService {
    @Autowired
    DeTaiRepository detaiRepo;
//    public void save(MultipartFile file) {
//        try {
//            List<Detai> teaList = GiangVienExcelUtility.excelToTeacherList(file.getInputStream());
//            detaiRepo.saveAll(teaList);
//        } catch (IOException ex) {
//            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
//        }
//    }
}
