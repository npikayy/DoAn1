package khang.test.example.demo.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FIlE(1001, "Lỗi, vui lòng kiểm tra lại", HttpStatus.INTERNAL_SERVER_ERROR),
    Email_EXISTED(1002, "Email sinh viên đã tồn tại", HttpStatus.BAD_REQUEST),
    nienKhoa_EXISTED(1002, "Niên khoá đã tồn tại", HttpStatus.BAD_REQUEST),
    Nganh_EXISTED(1002, "Chuyên ngành đã tồn tại", HttpStatus.BAD_REQUEST),
    TenKhoa_EXISTED(1002, "Tên khoa đã tồn tại", HttpStatus.BAD_REQUEST),
    Hocvi_EXISTED(1002, "Học vị đã tồn tại", HttpStatus.BAD_REQUEST),
    MSSV_EXISTED(1003, "Mã số sinh viên đã tồn tại", HttpStatus.BAD_REQUEST),
    Teacher_Email_EXISTED(1002, "Email giảng viên đã tồn tại", HttpStatus.BAD_REQUEST),
    MSGV_EXISTED(1003, "Mã số giảng viên đã tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_Student_Email(1004, "Email phải có đuôi @student.edu.vn", HttpStatus.BAD_REQUEST),
    INVALID_Teacher_Email(1004, "Email phải có đuôi @teacher.edu.vn", HttpStatus.BAD_REQUEST),
    DUPLICATED_Email(1005, "Email bị trùng lặp", HttpStatus.BAD_REQUEST),
    Invalid_CreateDay(1006, "Ngày tạo đề tài không hợp lệ!", HttpStatus.BAD_REQUEST),
    InvalidDay(1006, "Ngày bắt đầu/kết thúc đề tài không hợp lệ!", HttpStatus.BAD_REQUEST),
    Invalid_EndDay(1006, "Ngày kết thúc đề tài không hợp lệ!", HttpStatus.BAD_REQUEST),
    Unnull_EndDay(1006, "Nếu đề tài đã hoàn thành vui lòng nhập vào ngày kết thúc đề tài !", HttpStatus.BAD_REQUEST),
    Invalid_Number(1007, "Số lượng sinh viên không được dưới 0!", HttpStatus.BAD_REQUEST),
    PhoneNumber_Existed(1008, "Số điện thoại đã tồn tại!", HttpStatus.BAD_REQUEST),
    PhoneNumber_Invalid(1008, "Số điện thoại phải từ 10 - 11 số!", HttpStatus.BAD_REQUEST)

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
