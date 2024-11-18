package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, String> {
    boolean existsByEmail(String email);

    boolean existsByMaGV(String email);

    boolean existsBySDT(String sdt);

    GiangVien findByMaGV(String masv);

    void deleteByMaGV(String magv);

    @Query("SELECT g FROM GiangVien g " +
            "WHERE (:chuyenNganh IS NULL OR g.chuyenNganh = :chuyenNganh) " +
            "AND (:tenKhoa IS NULL OR g.TenKhoa = :tenKhoa) " +
            "AND (:hocVi IS NULL OR g.hocvi = :hocVi)" +
            "AND (:tenGV IS NULL OR g.TenGV like %:tenGV%)" +
            "AND (:magv IS NULL OR g.maGV = :magv)"+
            "AND (:gioiTinh IS NULL OR g.gioiTinh = :gioiTinh)"
    )
    List<GiangVien> findByDieukien(String chuyenNganh, String tenKhoa, String hocVi, String tenGV, String magv, String gioiTinh);
}
