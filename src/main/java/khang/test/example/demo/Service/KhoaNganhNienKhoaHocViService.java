package khang.test.example.demo.Service;

import khang.test.example.demo.entity.Khoa;
import khang.test.example.demo.entity.chuyenNganh;
import khang.test.example.demo.entity.hocVi;
import khang.test.example.demo.entity.nienKhoa;
import khang.test.example.demo.repository.HocViRepository;
import khang.test.example.demo.repository.KhoaRepository;
import khang.test.example.demo.repository.NganhRepository;
import khang.test.example.demo.repository.NienKhoaRepository;
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
}
