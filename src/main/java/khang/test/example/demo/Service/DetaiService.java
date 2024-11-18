package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.mapper.Mapper;
import khang.test.example.demo.repository.admin_repository.DeTaiRepository;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DetaiService {
    @Autowired
    DeTaiRepository detaiRepo;
    @Autowired
    ThongBaoRepository tbaoRepo;
    @Autowired
    Mapper dtMapper;

    public List<Detai> findAll() {
        return detaiRepo.findAll();
    }

    public List<Detai> timDeTai(String tenKhoa, String tinhTrang, String maGV, String madetai, LocalDate ngayBatdau, LocalDate ngayKetthuc) {
        return detaiRepo.findByDieukien(tenKhoa, tinhTrang, maGV, madetai, ngayBatdau, ngayKetthuc);
    }

    public void save(MultipartFile file) {
        try {
            List<Detai> danhSachDetai = DetaiExcelUtility.excelToDetaiList(file.getInputStream());
            detaiRepo.saveAll(danhSachDetai);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    public Detai capNhatDT(String madetai, Detai newDetai) {
        Detai detai = detaiRepo.findByMadetai(madetai);
        if (newDetai.getNgayKetthuc() == null) {

            if (newDetai.getNgayTaoDetai().isAfter(newDetai.getNgayBatdau())) {
                throw new AppException(ErrorCode.Invalid_CreateDay);
            }
            if ("Đã hoàn thành".equals(newDetai.getTinhtrang())){
                throw new AppException(ErrorCode.Unnull_EndDay);
            }
            if (newDetai.getSoLuongThanhVien() <= 0) {
                throw new AppException(ErrorCode.Invalid_Number);
            }
            dtMapper.capNhatDT2(detai, newDetai);
        } else {
            if (newDetai.getNgayTaoDetai().isAfter(newDetai.getNgayBatdau()) || newDetai.getNgayTaoDetai().isAfter(newDetai.getNgayKetthuc())) {
                throw new AppException(ErrorCode.Invalid_CreateDay);
            }
            if (newDetai.getNgayBatdau().isAfter(newDetai.getNgayKetthuc())) {
                throw new AppException(ErrorCode.InvalidDay);
            }
            if (newDetai.getSoLuongThanhVien() <= 0) {
                throw new AppException(ErrorCode.Invalid_Number);
            }
            newDetai.setTinhtrang("Đã hoàn thành");

            dtMapper.capNhatDT(detai, newDetai);
        }
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã cập nhật thông tin của đề tài có mã là " + madetai)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return detaiRepo.save(detai);
    }

    public void xoaDeTai(String madetai) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa đề tài có mã là "+ madetai)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        detaiRepo.deleteByMadetai(madetai);
    }

    public void XoaTatcaDetai() {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa tất cả đề tài")
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        detaiRepo.deleteAll();
    }
}
