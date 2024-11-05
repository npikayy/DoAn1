package khang.test.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import java.io.File;
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
    String maGiangvien;
    Integer soLuongThanhVien;
    @Column(columnDefinition = "nvarchar(255)")
    String tenKhoa;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayTaoDetai;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayBatdau;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayKetthuc;
    @Column(columnDefinition = "nvarchar(255)")
    String Tinhtrang;
    @Column(columnDefinition = "nvarchar(255)")
    File FileDeCuong;

}
