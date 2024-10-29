package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, String> {
    boolean existsByMSSV(String mssv);
    @Query(value = "SELECT s from Students s where s.MSSV like %?1%")
    List<Students> searchByMssv(String mssv);
}
