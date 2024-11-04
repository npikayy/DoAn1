package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoaRepository extends JpaRepository<Khoa, String> {
    boolean existsByTenKhoa(String tenKhoa);
}
