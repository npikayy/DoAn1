package khang.test.example.demo.Service;

import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.mapper.Mapper;
import khang.test.example.demo.repository.admin_repository.SinhVienRepository;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SinhVienService {
    @Autowired
    SinhVienRepository svRepo;

    @Autowired
    ThongBaoRepository tbaoRepo;

    @Autowired
    Mapper svMapper;

    public List<SinhVien> save(MultipartFile file) throws IOException {
        List<SinhVien> stuList = SinhVienExcelUtility.excelToStuList(file.getInputStream());
        return svRepo.saveAll(stuList);
    }

    public List<SinhVien> findAll() {
        return svRepo.findAll();
    }

    public List<SinhVien> timSinhVien(String chuyenNganh, String tenKhoa, Integer nienKhoa, String tenSV, String mssv) {
        return svRepo.findByDieukien(chuyenNganh, tenKhoa, nienKhoa, tenSV, mssv);
    }

    public SinhVien TaoSVmoi(SinhVien sinhVien) {
        String duoiEmail = "@student.edu.vn";
        if (svRepo.existsByMSSV(sinhVien.getMSSV()))
            throw new AppException(ErrorCode.MSSV_EXISTED);
        if (!sinhVien.getEmail().endsWith(duoiEmail))
            throw new AppException(ErrorCode.INVALID_Student_Email);
        if (svRepo.existsByEmail(sinhVien.getEmail()))
            throw new AppException(ErrorCode.Email_EXISTED);
        SinhVien svMoi = svMapper.TaoSVmoi(sinhVien);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm 1 sinh viên mới bằng form")
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);

        return svRepo.save(svMoi);

    }

    public SinhVien capNhatSV(String mssv, SinhVien newSinhVien) {
        SinhVien sinhVien = svRepo.findByMSSV(mssv);
        String duoiEmail = "@student.edu.vn";
        if (!newSinhVien.getEmail().endsWith(duoiEmail))
            throw new AppException(ErrorCode.INVALID_Student_Email);
        boolean giongEmailSV = sinhVien.getEmail().equals(newSinhVien.getEmail());
        if (svRepo.existsByEmail(newSinhVien.getEmail()) && !giongEmailSV)
            throw new AppException(ErrorCode.Email_EXISTED);
        svMapper.capNhatSV(sinhVien, newSinhVien);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã cập nhật thông tin của sinh viên có mã là " + mssv)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return svRepo.save(sinhVien);
    }

    public void xoaSinhVien(String mssv) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa sinh viên có mã là "+mssv)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        svRepo.deleteByMSSV(mssv);
    }

    public void xoaAllSinhVien() {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa tất cả sinh viên")
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        svRepo.deleteAll();
    }
}
