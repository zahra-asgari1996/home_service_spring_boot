package ir.maktab.service.mapper;

import ir.maktab.data.domain.Users;
import ir.maktab.dto.UserDto;

public interface UserMapper {
    Users toUser(UserDto dto);

    UserDto toUserDto(Users user);
}

