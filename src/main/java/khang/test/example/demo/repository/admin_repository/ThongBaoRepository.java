package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThongBaoRepository extends JpaRepository<ThongBao, String> {
}
