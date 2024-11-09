package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.hocVi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HocViRepository extends JpaRepository<hocVi, String> {
    boolean existsByHocVi(String hocVi);

    void deleteByHocVi(String hocvi);
}
