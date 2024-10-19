package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Integer> {
    boolean existsByMaGV(Integer magv);
}
