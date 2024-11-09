package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeTaiRepository extends JpaRepository<Detai, String> {
    boolean existsByMadetai(String madetai);

    Detai findByMadetai(String madetai);

    void deleteByMadetai(String madetai);

    @Query("SELECT d FROM Detai d " +
            "WHERE (:tenKhoa IS NULL OR d.tenKhoa = :tenKhoa) " +
            "AND (:tinhTrang IS NULL OR d.Tinhtrang = :tinhTrang) " +
            "AND (:maGV IS NULL OR d.maGiangvien = :maGV)" +
            "AND (:madetai IS NULL OR d.madetai = :madetai)")
    List<Detai> findByDieukien(String tenKhoa, String tinhTrang, String maGV, String madetai);
}
