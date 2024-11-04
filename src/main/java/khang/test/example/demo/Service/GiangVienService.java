package khang.test.example.demo.Service;

import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.mapper.Mapper;
import khang.test.example.demo.repository.admin_repository.GiangVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GiangVienService {
    @Autowired
    GiangVienRepository gvRepo;

    @Autowired
    Mapper gvMapper;
    public void save(MultipartFile file) {
        try {
            List<GiangVien> teaList = GiangVienExcelUtility.excelToTeacherList(file.getInputStream());
            gvRepo.saveAll(teaList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }
    public List<GiangVien> findAll() {
        return gvRepo.findAll();
    }

    public List<GiangVien> timGiangVien(String chuyenNganh, String tenKhoa, String hocVi, String tenGV, String magv)
    { return gvRepo.findByDieukien(chuyenNganh, tenKhoa, hocVi, tenGV, magv); }

    public void capNhatGV(String magv, GiangVien newGiangVien) {
        GiangVien giangVien = gvRepo.findByMaGV(magv);
        gvMapper.capNhatGV(giangVien, newGiangVien);
        gvRepo.save(giangVien);
    }

    public void xoaGiangVien(String magv) {
        gvRepo.deleteByMaGV(magv);
    }
}
