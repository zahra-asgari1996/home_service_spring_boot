package ir.maktab.web;

import ir.maktab.data.enums.UserRole;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.ManagerDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundCustomerException;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.validation.LoginValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {
    private final CustomerService customerService;
    private final ExpertService expertService;

    public Home(CustomerService customerService, ExpertService expertService) {
        this.customerService = customerService;
        this.expertService = expertService;
    }

    @GetMapping
    public String goToHome() {
        return "home";
    }

    @GetMapping(value = "/managerPage")
    public ModelAndView goToLoginManagerPage() {
        //return "managerLoginPage";
        return new ModelAndView("managerLoginPage", "manager", new ManagerDto());
    }

    @GetMapping(value = "/expert")
    public String goToRegisterExpertPage() {
        return "expertPage";
        //return new ModelAndView("expertPage", "expert", new ExpertDto());
    }

    @GetMapping(value = "/customer")
    public String goToRegisterCustomerPage() {
        return "customerPage";
        //return new ModelAndView("customerPage", "customer", new CustomerDto());
    }

    @GetMapping(value = "/login")
    public ModelAndView loginUsers() {
        return new ModelAndView("loginUsers", "loginUser", new UserDto());
    }

    @PostMapping("/login")
    public String loginUsers(@ModelAttribute("loginUser") @Validated(LoginValidation.class) UserDto userDto)
            throws NotFoundExpertException, InvalidPassword, NotFoundCustomerException {

        if (userDto.getUserRole().equals(UserRole.Expert)) {
            ExpertDto expertDto = new ExpertDto();
            expertDto.setEmail(userDto.getEmail());
            expertDto.setPassword(userDto.getPassword());
            expertDto.setUserRole(UserRole.Expert);
            expertService.loginExpert(expertDto);
            return "expertHomePage";
        }
        if (userDto.getUserRole().equals(UserRole.Customer)) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setEmail(userDto.getEmail());
            customerDto.setPassword(userDto.getPassword());
            customerDto.setUserRole(UserRole.Customer);
            customerService.loginCustomer(customerDto);
            return "customerHomepage";
        }
        return "home";
    }

}
