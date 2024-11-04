package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.chuyenNganh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NganhRepository extends JpaRepository<chuyenNganh, String> {
    boolean existsByChuyenNganh(String chuyenNganh);
}
