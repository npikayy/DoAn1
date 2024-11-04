package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, String> {
    boolean existsByMSSV(String mssv);

    void deleteByMSSV(String mssv);

    SinhVien findByMSSV(String mssv);
    @Query("SELECT s FROM SinhVien s " +
            "WHERE (:chuyenNganh IS NULL OR s.chuyenNganh = :chuyenNganh) " +
            "AND (:tenKhoa IS NULL OR s.TenKhoa = :tenKhoa) " +
            "AND (:nienKhoa IS NULL OR s.nienKhoa = :nienKhoa)" +
            "AND (:tenSV IS NULL OR s.TenSV like %:tenSV%)" +
            "AND (:mssv IS NULL OR s.MSSV = :mssv)")
    List<SinhVien> findByDieukien(String chuyenNganh, String tenKhoa, Integer nienKhoa, String tenSV, String mssv);

}
