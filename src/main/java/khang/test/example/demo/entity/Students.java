package khang.test.example.demo.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Students {
        @Id
        Integer MSSV;
        @Column(columnDefinition = "nvarchar(255)")
        String studentName;
        Integer mobileNo;
        @Column(columnDefinition = "nvarchar(255)")
        String Lop;
        Integer nienKhoa;
        Integer maNhom;
        @Column(columnDefinition = "Date")
        Date ngaySinh;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ma_tai_khoan", referencedColumnName = "Ma_Tai_Khoan")
        Accounts maTK;
}
