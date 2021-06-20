package ir.maktab.service;

import ir.maktab.data.domain.Users;
import ir.maktab.data.enums.UserSituation;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.data.repository.UserSpecification;
import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserHistoryDto;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        return repository.findAll()
                .stream().map
                        (i -> mapper.toUserDto(i))
                .collect(Collectors.toList());

    }

    @Override
    public void save(UserDto userDto) {

        repository.save(mapper.toUser(userDto));
    }

    @Override
    public List<UserDto> filterUsers(FilterUsersDto dto) {
        return repository.findAll(UserSpecification.filterUsers(dto)).stream().map(i -> mapper.toUserDto(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> userHistory(UserHistoryDto dto) {
        return repository.findAll(UserSpecification.userHistory(dto)).stream().map(i->mapper.toUserDto(i)).collect(Collectors.toList());
    }

    @Override
    public UserDto confirmUser(Integer id) throws NotFoundExpertException {
        Optional<Users> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent()){
            throw new NotFoundExpertException("not.found.expert");
        }
        optionalUser.get().setSituation(UserSituation.Accepted);
        repository.save(optionalUser.get());
        return mapper.toUserDto(optionalUser.get());
    }

//    @Override
//    public void changePassword(UserDto dto) {
//        repository.changePassword(mapper.toUser(dto));
//
//    }
//
//    @Override
//    public List<UserDto> findByProperty(SearchCustomerDto dto) {
//
//        return repository.findByProperty(dto)
//                .stream().map
//                        (i->mapper.toUserDto(i)).collect(Collectors.toList());
//    }
}
