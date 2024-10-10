package khang.test.example.demo.controller;

import khang.test.example.demo.Service.AuthService;
import khang.test.example.demo.dto.request.AuthenticationRequest;
import khang.test.example.demo.response.apiResponse;
import khang.test.example.demo.response.AuthResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    @PostMapping("/log-in")
    apiResponse<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authService.Authenticate(request);
        return apiResponse.<AuthResponse>builder()
                .result(result)
                .build();
    }
}
