package khang.test.example.demo.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FIlE(1001, "Lỗi, vui lòng kiểm tra lại", HttpStatus.INTERNAL_SERVER_ERROR),
    Email_EXISTED(1002, "Email sinh viên đã tồn tại", HttpStatus.BAD_REQUEST),
    MSSV_EXISTED(1003, "Mã số sinh viên đã tồn tại", HttpStatus.BAD_REQUEST),
    Teacher_Email_EXISTED(1002, "Email giảng viên đã tồn tại", HttpStatus.BAD_REQUEST),
    MSGV_EXISTED(1003, "Mã số giảng viên đã tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_Student_Email(1004, "Email phải có đuôi @student.edu.vn", HttpStatus.BAD_REQUEST),
    INVALID_Teacher_Email(1004, "Email phải có đuôi @teacher.edu.vn", HttpStatus.BAD_REQUEST),
    DUPLICATED_Email(1005, "Email bị trùng lặp", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
