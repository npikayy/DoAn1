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
public class SinhVien {
        @Id
        String MSSV;
        @Column(columnDefinition = "nvarchar(255)")
        String TenSV;
        @Column(columnDefinition = "Date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate ngaySinh;
        Integer SDT;
        @Column(columnDefinition = "nvarchar(255)")
        String email;
        String Lop;
        Integer nienKhoa;
        @Column(columnDefinition = "nvarchar(255)")
        String chuyenNganh;
        @Column(columnDefinition = "nvarchar(255)")
        String TenKhoa;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ma_tai_khoan", referencedColumnName = "Ma_Tai_Khoan")
        Accounts thongTinTK;

}
