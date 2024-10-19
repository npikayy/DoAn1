package khang.test.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teachers {
    @Id
    Integer maGV;
    @Column(columnDefinition = "nvarchar(255)")
    String TenGV;
    @Column(columnDefinition = "nvarchar(255)")
    String TenNganh;
    @Column(columnDefinition = "nvarchar(255)")
    String TenKhoa;
    Integer maKhoa;
    @Column(columnDefinition = "Date")
    Date ngaySinh;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_tai_khoan", referencedColumnName = "Ma_Tai_Khoan")
    Accounts maTK;
}
