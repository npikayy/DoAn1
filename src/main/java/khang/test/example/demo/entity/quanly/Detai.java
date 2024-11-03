package khang.test.example.demo.entity.quanly;

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
public class Detai {
    @Id
    String madetai;
    @Column(columnDefinition = "nvarchar(255)")
    String tendetai;
    @Column(columnDefinition = "nvarchar(255)")
    String tenGiangvien;
    @Column(columnDefinition = "nvarchar(255)")
    String tenSinhvien;
    @Column(columnDefinition = "nvarchar(255)")
    String Khoa;
    @Column(columnDefinition = "nvarchar(255)")
    String Nganh;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate ngayBatdau;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate ngayKetthuc;
    @Column(columnDefinition = "nvarchar(255)")
    String Tinhtrang;
    @Column(columnDefinition = "nvarchar(255)")
    String Filedetai;
    @Column(columnDefinition = "nvarchar(255)")
    Double Diem;
}
