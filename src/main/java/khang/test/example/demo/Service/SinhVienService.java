package khang.test.example.demo.Service;
import java.io.IOException;
import java.util.List;

import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.mapper.Mapper;
import khang.test.example.demo.repository.admin_repository.SinhVienRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Slf4j
public class SinhVienService {
    @Autowired
    SinhVienRepository svRepo;

    @Autowired
    Mapper svMapper;
    public void save(MultipartFile file) {
        try {
            List<SinhVien> stuList = SinhVienExcelUtility.excelToStuList(file.getInputStream());
            svRepo.saveAll(stuList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<SinhVien> findAll() {
        return svRepo.findAll();
    }

    public List<SinhVien> timSinhVien(String chuyenNganh, String tenKhoa, Integer nienKhoa, String tenSV, String mssv)
    { return svRepo.findByDieukien(chuyenNganh, tenKhoa, nienKhoa, tenSV, mssv); }

    public void capNhatSV(String mssv, SinhVien newSinhVien) {
        SinhVien sinhVien = svRepo.findByMSSV(mssv);
        svMapper.capNhatSV(sinhVien, newSinhVien);
        svRepo.save(sinhVien);
    }

    public void xoaSinhVien(String mssv) {
        svRepo.deleteByMSSV(mssv);
    }
}
