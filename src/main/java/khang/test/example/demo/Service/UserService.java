package khang.test.example.demo.Service;

import khang.test.example.demo.dto.request.UserCreationRequest;
import khang.test.example.demo.dto.request.UserUpdateRequest;
import khang.test.example.demo.entity.Users;
import khang.test.example.demo.exeption.AppException;
import khang.test.example.demo.exeption.ErrorCode;
import khang.test.example.demo.mapper.userMapper;
import khang.test.example.demo.repository.UserRepository;
import khang.test.example.demo.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    userMapper userMapper;
    PasswordEncoder passwordEncoder;
    public List<UserResponse> showUsers() {
        log.info("method get users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse showUserbyID(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Users user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo(){
    var context = SecurityContextHolder.getContext();
    String name = context.getAuthentication().getName();

    Users user = userRepository.findByUsername(name).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
    return userMapper.toUserResponse(user);
    }

    public UserResponse UpdateUser(String userId, UserUpdateRequest request) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        return userMapper.toUserResponse(userRepository.save(user));

    }

    public void DeleteAllUsers() {
        userRepository.deleteAll();
    }

    public void DeleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
