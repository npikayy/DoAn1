package khang.test.example.demo.dto.request;

import jakarta.validation.constraints.Size;
import khang.test.example.demo.exeption.ErrorCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String name;
    int age;

}
