package khang.test.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThongBao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idTbao;
    @Column(columnDefinition = "nvarchar(255)")
    String noiDungTbao;
    @Column(columnDefinition = "DateTime")
    @JsonFormat(pattern = "dd/MM/yyyy hh:m")
    LocalDateTime ngayThucHien;
    @Column(columnDefinition = "nvarchar(255)")
    String nguoiThucHien;
}
