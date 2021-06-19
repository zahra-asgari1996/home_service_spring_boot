package ir.maktab.service;

import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> fetchAllUsers();

    void save(UserDto userDto);

    //    void changePassword(UserDto dto);
//    List<UserDto> findByProperty(SearchCustomerDto dto);
    List<UserDto> filterUsers(FilterUsersDto dto);
}
