package khang.test.example.demo.mapper;

import khang.test.example.demo.dto.request.UserCreationRequest;
import khang.test.example.demo.dto.request.UserUpdateRequest;
import khang.test.example.demo.entity.Users;
import khang.test.example.demo.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface userMapper {
    Users toUser(UserCreationRequest request);
    UserResponse toUserResponse(Users users);
    void updateUser(@MappingTarget Users user, UserUpdateRequest request);
}
