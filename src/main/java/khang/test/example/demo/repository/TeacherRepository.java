package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Students;
import khang.test.example.demo.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String> {
    boolean existsByMaGV(String magv);

    @Query(value = "SELECT t from Teachers t where t.maGV like %?1%")
    List<Teachers> searchByMaGV(String magv);
}
