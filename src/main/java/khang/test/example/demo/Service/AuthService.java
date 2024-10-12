package khang.test.example.demo.Service;

import khang.test.example.demo.dto.request.AuthenticationRequest;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.response.AuthResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    @NonFinal
    @Value("${jwt.key-abc}")
    String Signer_Key; //= "ADQbLUG7grHERg3yaK/Vho+512TQ1/IJ6axLXuYzOxG/bE5Jue1qEhpwkIZIE/Bz";

//    public AuthResponse Authenticate(AuthenticationRequest request){
//        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//        return AuthResponse.builder()
//                .Authenticated(authenticated)
//                .build();
//    }

}
