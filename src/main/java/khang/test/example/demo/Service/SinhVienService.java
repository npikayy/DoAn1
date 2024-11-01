package khang.test.example.demo.Service;
import java.io.IOException;
import java.util.List;

import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class SinhVienService {
    @Autowired
    SinhVienRepository stuRepo;
    public void save(MultipartFile file) {
        try {
            List<SinhVien> stuList = SinhVienExcelUtility.excelToStuList(file.getInputStream());
            stuRepo.saveAll(stuList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<SinhVien> findAll() {
        return stuRepo.findAll();
    }

    public List<SinhVien> timSinhVien(String chuyenNganh, String tenKhoa, Integer nienKhoa, String tenSV, String mssv)
    { return stuRepo.findByDieukien(chuyenNganh, tenKhoa, nienKhoa, tenSV, mssv); }

}
