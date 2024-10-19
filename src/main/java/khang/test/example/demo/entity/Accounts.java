package khang.test.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Ma_Tai_Khoan")
    String maTK;
    String username;
    String password;
    String email;
    String LoaiTK;
}
