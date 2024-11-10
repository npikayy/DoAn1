package khang.test.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiangVien {
    @Id
    String maGV;
    @Column(columnDefinition = "nvarchar(255)")
    String TenGV;
    @Column(columnDefinition = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
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
}
