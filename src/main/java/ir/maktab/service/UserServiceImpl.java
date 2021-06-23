package ir.maktab.service;

import ir.maktab.data.domain.Users;
import ir.maktab.data.enums.UserSituation;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.data.repository.UserSpecification;
import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserHistoryDto;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundUserException;
import ir.maktab.service.mapper.UserMapper;
import net.bytebuddy.utility.RandomString;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, MessageSource messageSource, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.mapper = mapper;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        return repository.findAll()
                .stream().map
                        (i -> mapper.toUserDto(i))
                .collect(Collectors.toList());

    }

    @Override
    public void save(UserDto userDto,String siteURL) throws UnsupportedEncodingException, MessagingException {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        userDto.setVerificationCode(randomCode);
        userDto.setEnabled(false);

        repository.save(mapper.toUser(userDto));
        sendVerificationEmail(userDto, siteURL);


    }

    @Override
    public void sendVerificationEmail(UserDto user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "Your email address";
        String senderName = "Your company name";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        javaMailSender.send(message);

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
            throw new NotFoundExpertException(messageSource.getMessage("not.found.expert",null,new Locale("fa_ir")));
        }
        optionalUser.get().setUserSituation(UserSituation.Accepted);
        repository.save(optionalUser.get());
        return mapper.toUserDto(optionalUser.get());
    }

    @Override
    public UserDto findById(Integer id) throws NotFoundUserException {
        Optional<Users> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent()){
            throw new NotFoundUserException(messageSource.getMessage("not.found.user",null,new Locale("fa_ir")));
        }
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
