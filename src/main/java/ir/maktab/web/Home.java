package ir.maktab.web;

import ir.maktab.dto.ManagerDto;
import ir.maktab.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

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


}
