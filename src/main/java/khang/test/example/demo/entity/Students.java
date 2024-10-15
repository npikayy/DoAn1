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
public class Students {
        @Id
        Integer MSSV;
        @Column(columnDefinition = "nvarchar(255)")
        String studentName;
        Integer mobileNo;
        String email;
        @Column(columnDefinition = "nvarchar(255)")
        String Lop;
        Integer nienKhoa;
        Integer maNhom;
        @Column(columnDefinition = "Date")
        Date ngaySinh;
}
