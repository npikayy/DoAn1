package khang.test.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Accounts<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Ma_Tai_Khoan")
    String maTK;
    @Column(columnDefinition = "nvarchar(255)")
    String TenNguoiDung;
    String username;
    String password;
    @Column(columnDefinition = "nvarchar(255)")
    String LoaiTK;
}
