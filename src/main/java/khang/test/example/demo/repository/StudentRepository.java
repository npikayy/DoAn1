package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {
    boolean existsByMSSV(Integer mssv);
}
