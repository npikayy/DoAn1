package khang.test.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThongBao {
    @Id
    @Column(columnDefinition = "nvarchar(255)")
    String noiDungTbao;
    @Column(columnDefinition = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayThucHien;
    @Column(columnDefinition = "nvarchar(255)")
    String nguoiThucHien;
}
