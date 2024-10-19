package khang.test.example.demo.repository;

import khang.test.example.demo.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, String> {
}
