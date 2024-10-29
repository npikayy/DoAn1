package khang.test.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teachers {
    @Id
    String maGV;
    @Column(columnDefinition = "nvarchar(255)")
    String TenGV;
    String email;
    @Column(columnDefinition = "nvarchar(255)")
    String TenNganh;
    @Column(columnDefinition = "nvarchar(255)")
    String TenKhoa;
    Integer maKhoa;
    @Column(columnDefinition = "Date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate ngaySinh;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_tai_khoan", referencedColumnName = "Ma_Tai_Khoan")
    Accounts thongTinTK;
}
