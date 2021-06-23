package ir.maktab.service;

import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserHistoryDto;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundUserException;

import java.util.List;

public interface UserService {
    List<UserDto> fetchAllUsers();

    void save(UserDto userDto);
    void sendVerificationEmail(UserDto user, String siteURL);

    //    void changePassword(UserDto dto);
//    List<UserDto> findByProperty(SearchCustomerDto dto);
    List<UserDto> filterUsers(FilterUsersDto dto);
    List<UserDto> userHistory(UserHistoryDto dto);

    UserDto confirmUser(Integer id) throws NotFoundExpertException;
    UserDto findById(Integer id) throws NotFoundUserException;
}
