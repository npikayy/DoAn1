package khang.test.example.demo.config;

import khang.test.example.demo.entity.Accounts;
import khang.test.example.demo.repository.admin_repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {

    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepo){

        return args -> {
            if (!accountRepo.existsByUsername("admin")){
               Accounts user = Accounts.builder()
                        .username("admin")
                        .password("admin")
                        .TenNguoiDung("khang")
                        .LoaiTK("Quản lý")
                        .build();
                accountRepo.save(user);
                log.warn("admin user has been created with default password: admin");
            }
        };
    };
}
