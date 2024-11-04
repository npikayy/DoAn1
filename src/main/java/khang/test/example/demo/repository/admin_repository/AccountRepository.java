package khang.test.example.demo.repository.admin_repository;

import khang.test.example.demo.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, String> {
    boolean existsByUsername(String username);
}
