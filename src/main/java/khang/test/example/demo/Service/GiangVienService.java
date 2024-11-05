package khang.test.example.demo.Service;

import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
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
    public List<GiangVien> save(MultipartFile file) throws IOException {
            List<GiangVien> teaList = GiangVienExcelUtility.excelToTeacherList(file.getInputStream());
        return gvRepo.saveAll(teaList);
    }
    public List<GiangVien> findAll() {
        return gvRepo.findAll();
    }

    public List<GiangVien> timGiangVien(String chuyenNganh, String tenKhoa, String hocVi, String tenGV, String magv)
    { return gvRepo.findByDieukien(chuyenNganh, tenKhoa, hocVi, tenGV, magv); }

    public GiangVien TaoGVmoi(GiangVien giangVien){
        String duoiEmail = "@teacher.edu.vn";
        if (gvRepo.existsByMaGV(giangVien.getMaGV()))
            throw new AppException(ErrorCode.MSSV_EXISTED);
        if (!giangVien.getEmail().endsWith(duoiEmail))
            throw new AppException(ErrorCode.INVALID_Teacher_Email);
        if (gvRepo.existsByEmail(giangVien.getEmail()))
            throw new AppException(ErrorCode.Email_EXISTED);
        GiangVien gvMoi = gvMapper.TaoGVmoi(giangVien);
        return gvRepo.save(gvMoi);
    }
    public void capNhatGV(String magv, GiangVien newGiangVien) {
        GiangVien giangVien = gvRepo.findByMaGV(magv);
        gvMapper.capNhatGV(giangVien, newGiangVien);
        gvRepo.save(giangVien);
    }

    public void xoaGiangVien(String magv) {
        gvRepo.deleteByMaGV(magv);
    }
}
