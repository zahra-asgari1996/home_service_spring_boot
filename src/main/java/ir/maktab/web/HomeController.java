package ir.maktab.web;

import ir.maktab.data.enums.UserRole;
import ir.maktab.dto.ManagerDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundCustomerException;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.validation.LoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController  implements ErrorController {
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final static Logger logger= LogManager.getLogger(HomeController.class);

    public HomeController(CustomerService customerService, ExpertService expertService) {
        this.customerService = customerService;
        this.expertService = expertService;
    }
    @RequestMapping("/error")
    public String handleError() {
        return "accessDeniedPage";
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



    @GetMapping(value = "/userLogin")
    public ModelAndView loginUsers() {
        logger.info("get method");
        return new ModelAndView("loginUsers", "loginUser", new UserDto());
    }

    @GetMapping(value = "/managerLogout")
    public String managerLogout() {
        return "home";
    }

    @GetMapping(value = "/userLogout")
    public String userLogout() {
        return "home";
    }

//    @GetMapping("/customerHomePage")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public String goToHomePage(){
//        return "customerHomePage";
//    }

//    @PostMapping("/userLogin")
//    public String loginUsers(@ModelAttribute("loginUser") @Validated(LoginValidation.class) UserDto userDto,Model model)
//            throws NotFoundExpertException, InvalidPassword, NotFoundCustomerException {
//        model.addAttribute("user",userDto);
//        if (userDto.getUserRole().equals(UserRole.EXPERT)) {
//            logger.info("expert page");
//            return "expertHomePage";
//        }
//        if (userDto.getUserRole().equals(UserRole.CUSTOMER)) {
//            logger.info("customer page");
//            return "customerHomePage";
//        }
//        return "home";
//    }

    @GetMapping("/loginFailed")
    public ModelAndView errorHandler(Model model){
        model.addAttribute("error","your information is not correct");
        logger.warn("your information is not correct");
        return new ModelAndView("loginUsers", "loginUser", new UserDto());
    }

    @GetMapping("/mapp")
    public String goToMapp() {
        return "mapp";
    }

}
