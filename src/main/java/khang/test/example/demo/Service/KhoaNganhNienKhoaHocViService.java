package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Khoa;
import khang.test.example.demo.entity.chuyenNganh;
import khang.test.example.demo.entity.hocVi;
import khang.test.example.demo.entity.nienKhoa;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.repository.admin_repository.HocViRepository;
import khang.test.example.demo.repository.admin_repository.KhoaRepository;
import khang.test.example.demo.repository.admin_repository.NganhRepository;
import khang.test.example.demo.repository.admin_repository.NienKhoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return nienKhoaRepo.save(nienKhoa);
    }

    public chuyenNganh TaoNganhmoi(chuyenNganh ChuyenNganh) {
        if (nganhRepo.existsByChuyenNganh(ChuyenNganh.getChuyenNganh()))
            throw new AppException(ErrorCode.Nganh_EXISTED);
        return nganhRepo.save(ChuyenNganh);
    }

    public Khoa TaoKhoamoi(Khoa khoa) {
        if (khoaRepo.existsByTenKhoa(khoa.getTenKhoa()))
            throw new AppException(ErrorCode.TenKhoa_EXISTED);

        return khoaRepo.save(khoa);
    }

    public hocVi TaoHocVimoi(hocVi HocVi) {
        if (hocViRepo.existsByHocVi(HocVi.getHocVi()))
            throw new AppException(ErrorCode.Hocvi_EXISTED);

        return hocViRepo.save(HocVi);
    }

    public void XoaKhoa(String tenKhoa) {
        khoaRepo.deleteByTenKhoa(tenKhoa);
    }

    public void XoaNganh(String chuyenNganh) {
        nganhRepo.deleteByChuyenNganh(chuyenNganh);
    }

    public void XoaNienKhoa(Integer nienKhoa) {
        nienKhoaRepo.deleteByNienKhoa(nienKhoa);
    }

    public void XoaHocVi(String hocVi) {
        hocViRepo.deleteByHocVi(hocVi);
    }
}
