package khang.test.example.demo.Service;

import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.mapper.Mapper;
import khang.test.example.demo.repository.admin_repository.GiangVienRepository;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiangVienService {
    @Autowired
    GiangVienRepository gvRepo;
    @Autowired
    ThongBaoRepository tbaoRepo;
    @Autowired
    Mapper gvMapper;

    public List<GiangVien> save(MultipartFile file) throws IOException {
        List<GiangVien> teaList = GiangVienExcelUtility.excelToTeacherList(file.getInputStream());
        return gvRepo.saveAll(teaList);
    }

    public List<GiangVien> findAll() {
        return gvRepo.findAll();
    }

    public List<GiangVien> timGiangVien(String chuyenNganh, String tenKhoa, String hocVi, String tenGV, String magv) {
        return gvRepo.findByDieukien(chuyenNganh, tenKhoa, hocVi, tenGV, magv);
    }

    public GiangVien TaoGVmoi(GiangVien giangVien) {
        String duoiEmail = "@teacher.edu.vn";
        if (gvRepo.existsByMaGV(giangVien.getMaGV()))
            throw new AppException(ErrorCode.MSGV_EXISTED);
        if (!giangVien.getEmail().endsWith(duoiEmail))
            throw new AppException(ErrorCode.INVALID_Teacher_Email);
        if (gvRepo.existsByEmail(giangVien.getEmail()))
            throw new AppException(ErrorCode.Teacher_Email_EXISTED);
        GiangVien gvMoi = gvMapper.TaoGVmoi(giangVien);

        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm 1 giảng viên mới bằng form")
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("Khang")
                .build();
        tbaoRepo.save(thongBao);

        return gvRepo.save(gvMoi);
    }

    public GiangVien capNhatGV(String magv, GiangVien newGiangVien) {
        String duoiEmail = "@teacher.edu.vn";
        GiangVien giangVien = gvRepo.findByMaGV(magv);
        if (!newGiangVien.getEmail().endsWith(duoiEmail))
            throw new AppException(ErrorCode.INVALID_Teacher_Email);
        boolean giongEmailGV = giangVien.getEmail().equals(newGiangVien.getEmail());
        if (gvRepo.existsByEmail(newGiangVien.getEmail()) && !giongEmailGV)
            throw new AppException(ErrorCode.Teacher_Email_EXISTED);
        gvMapper.capNhatGV(giangVien, newGiangVien);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã cập nhật thông tin của giảng viên có mã là " + magv)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return gvRepo.save(giangVien);
    }

    public void xoaGiangVien(String magv) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa giảng viên có mã là " + magv)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        gvRepo.deleteByMaGV(magv);
    }

    public void xoaAllGiangVien() {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa tất cả giảng viên")
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        gvRepo.deleteAll();
    }
}
