package khang.test.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class chuyenNganh {
    @Id
    @Column(columnDefinition = "nvarchar(255)")
    String chuyenNganh;
}
