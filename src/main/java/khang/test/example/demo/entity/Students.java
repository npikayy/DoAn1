package khang.test.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Students {
        @Id
        Integer id;
        String studentName;
        Integer mobileNo;
        String email;
}
