package khang.test.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String maGV;
    @Column(columnDefinition = "nvarchar(255)")
    String TenGV;
    @Column(columnDefinition = "Date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate ngaySinh;
    String email;
    @Column(columnDefinition = "nvarchar(255)")
    String hocvi;
    @Column(columnDefinition = "nvarchar(255)")
    String chuyenNganh;
    @Column(columnDefinition = "nvarchar(255)")
    String TenKhoa;
    @Column(columnDefinition = "nvarchar(255)")
    String kinhNghiemGD;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_tai_khoan", referencedColumnName = "Ma_Tai_Khoan")
    Accounts thongTinTK;
}