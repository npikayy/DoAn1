package khang.test.example.demo.Service;

import khang.test.example.demo.entity.*;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.repository.admin_repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class KhoaNganhNienKhoaHocViService {

    @Autowired
    private NienKhoaRepository nienKhoaRepo;

    @Autowired
    private KhoaRepository khoaRepo;

    @Autowired
    NganhRepository nganhRepo;

    @Autowired
    HocViRepository hocViRepo;

    @Autowired
    private ThongBaoRepository tbaoRepo;

    public List<nienKhoa> findAllNienKhoa() {
        return nienKhoaRepo.findAll();
    }

    public List<Khoa> findAllKhoa() {
        return khoaRepo.findAll();
    }

    public List<chuyenNganh> findAllChuyenNganh() {
        return nganhRepo.findAll();
    }

    public List<hocVi> findAllHocVi() {
        return hocViRepo.findAll();
    }

    public nienKhoa TaoNienKhoamoi(nienKhoa nienKhoa) {
        if (nienKhoaRepo.existsByNienKhoa(nienKhoa.getNienKhoa()))
            throw new AppException(ErrorCode.nienKhoa_EXISTED);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm niên khóa " + nienKhoa.getNienKhoa())
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return nienKhoaRepo.save(nienKhoa);
    }

    public chuyenNganh TaoNganhmoi(chuyenNganh ChuyenNganh) {
        if (nganhRepo.existsByChuyenNganh(ChuyenNganh.getChuyenNganh()))
            throw new AppException(ErrorCode.Nganh_EXISTED);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm chuyên ngành " + ChuyenNganh.getChuyenNganh())
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return nganhRepo.save(ChuyenNganh);
    }

    public Khoa TaoKhoamoi(Khoa khoa) {
        if (khoaRepo.existsByTenKhoa(khoa.getTenKhoa()))
            throw new AppException(ErrorCode.TenKhoa_EXISTED);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm khoa " + khoa.getTenKhoa())
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return khoaRepo.save(khoa);
    }

    public hocVi TaoHocVimoi(hocVi HocVi) {
        if (hocViRepo.existsByHocVi(HocVi.getHocVi()))
            throw new AppException(ErrorCode.Hocvi_EXISTED);
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã thêm học vị " + HocVi.getHocVi())
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        return hocViRepo.save(HocVi);
    }

    public void XoaKhoa(String tenKhoa) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa khoa " + tenKhoa)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        khoaRepo.deleteByTenKhoa(tenKhoa);
    }

    public void XoaNganh(String chuyenNganh) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa chuyên ngành " + chuyenNganh)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        nganhRepo.deleteByChuyenNganh(chuyenNganh);
    }

    public void XoaNienKhoa(Integer nienKhoa) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa niên khóa " + nienKhoa)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        nienKhoaRepo.deleteByNienKhoa(nienKhoa);
    }

    public void XoaHocVi(String hocVi) {
        ThongBao thongBao = ThongBao.builder()
                .noiDungTbao("Người dùng đã xóa học vị " + hocVi)
                .ngayThucHien(LocalDateTime.now())
                .nguoiThucHien("ADMIN")
                .build();
        tbaoRepo.save(thongBao);
        hocViRepo.deleteByHocVi(hocVi);
    }
}
