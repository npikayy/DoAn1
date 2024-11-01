package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoaRepository extends JpaRepository<Khoa, String> {
    boolean existsByTenKhoa(String tenKhoa);
}
