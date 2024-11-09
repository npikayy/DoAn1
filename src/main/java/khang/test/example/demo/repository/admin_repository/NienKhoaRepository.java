package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.nienKhoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NienKhoaRepository extends JpaRepository<nienKhoa, Integer> {
    boolean existsByNienKhoa(Integer nienKhoa);

    void deleteByNienKhoa(Integer nienKhoa);
}
