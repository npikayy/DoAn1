package khang.test.example.demo.controller;


import jakarta.validation.Valid;
import khang.test.example.demo.Service.UserService;
import khang.test.example.demo.dto.request.UserUpdateRequest;
import khang.test.example.demo.response.apiResponse;
import khang.test.example.demo.dto.request.UserCreationRequest;
import khang.test.example.demo.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserController {
    UserService userService;

    @GetMapping()
    public apiResponse<List<UserResponse>> showAllUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());

        return apiResponse.<List<UserResponse>>builder()
                .result(userService.showUsers())
                .build();
    }

    @GetMapping("/{userId}")
    apiResponse<UserResponse> showUserById(@PathVariable String userId){
        return apiResponse.<UserResponse>builder()
                .result(userService.showUserbyID(userId))
                .build();
    }
    @GetMapping("/my-info")
    apiResponse<UserResponse> getInfo(){
        return apiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
    @PostMapping("/create-user")
    apiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return apiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PutMapping("/update-user/{userId}")
    apiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request){
        return apiResponse.<UserResponse>builder()
                .result(userService.UpdateUser(userId,request))
                .build();
    }
    @DeleteMapping("/delete-all-users")
    apiResponse<String> deleteAllUser(){
        userService.DeleteAllUsers();
        return apiResponse.<String>builder()
                .result("xóa r nhé :)")
                .build();
    }
    @DeleteMapping("/delete-user/{userId}")
    apiResponse<String> deleteUser(@PathVariable String userId){
        userService.DeleteUser(userId);
        return apiResponse.<String>builder()
                .result("xóa r nhé :)")
                .build();
    }
}
