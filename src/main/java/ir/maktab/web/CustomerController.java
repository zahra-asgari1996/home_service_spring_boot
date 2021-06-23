package ir.maktab.web;

import ir.maktab.dto.CreditCardInfo;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.CustomerService;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import ir.maktab.service.exception.*;
import ir.maktab.service.validation.ChangePasswordValidation;
import ir.maktab.service.validation.LoginValidation;
import ir.maktab.service.validation.RegisterValidation;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "/customer")
@SessionAttributes({"loginCustomer", "customer", "order"})
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OfferService offerService;
    private final MessageSource messageSource;


    public CustomerController(CustomerService customerService, OrderService orderService, OfferService offerService, MessageSource messageSource) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.offerService = offerService;
        this.messageSource = messageSource;
    }

    @PostMapping("/register")
    public String saveNewCustomer(@ModelAttribute("customer") @Validated(RegisterValidation.class) CustomerDto customerDto,
                                  Model model,HttpServletRequest request)
            throws DuplicatedEmailAddressException, UnsupportedEncodingException, MessagingException {

        CustomerDto customer = customerService.registerCustomer(customerDto,getSiteURL(request));
        model.addAttribute("credit", customer.getCredit());
        return "register_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/register")
    public ModelAndView goToRegisterPage() {
        return new ModelAndView("customerRegisterPage", "customer", new CustomerDto());
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (customerService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }


    @GetMapping("/login")
    public ModelAndView goToLoginPage() {
        return new ModelAndView("customerLoginPage", "loginCustomer", new CustomerDto());
    }


    @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("loginCustomer") @Validated(LoginValidation.class) CustomerDto dto, Model model)
            throws InvalidPassword, NotFoundCustomerException {

        System.out.println("customer home page");
        CustomerDto customer = customerService.loginCustomer(dto);
        model.addAttribute("credit", customer.getCredit());
        return "customerHomePage";
    }


    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePassword", new CustomerDto());
        return "customerPassChange";
    }


    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("changePassword") @Validated(ChangePasswordValidation.class) CustomerDto dto,
                                 HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        if (customer != null)
            dto.setEmail(customer.getEmail());
        customerService.changePassword(dto);
        if (loginCustomer != null)
            dto.setEmail(loginCustomer.getEmail());
        customerService.changePassword(dto);
        return "customerHomePage";
    }

    @GetMapping("/showOrders")
    public String showOrders(Model model, HttpServletRequest request)
            throws NotFoundOrderException, NotFoundCustomerException {

        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        if (customer != null)
            model.addAttribute("ordersList", orderService.findByCustomer(customer));
        if (loginCustomer != null)
            model.addAttribute("ordersList", orderService.findByCustomer(loginCustomer));
        return "showOrdersForCustomerHomePage";
    }


    @GetMapping("/showOffers")
    public String showOffers(HttpServletRequest request, Model model)
            throws NotFoundCustomerException,
            NotFoundOrderException {

        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        if (customer != null)
            model.addAttribute("offersList", offerService.getOrderOffersSortByRateAndPrice(customer));
        if (loginCustomer != null)
            model.addAttribute("offersList", offerService.getOrderOffersSortByRateAndPrice(loginCustomer));
        return "showOffersForCustomerHomePage";
    }

    @GetMapping("/paymentFromAccountCredit/{id}")
    public String paymentFromAccountCredit(@PathVariable("id") Integer id, HttpServletRequest request) throws
            NotFoundCustomerException, NotEnoughAccountBalance, NotFoundOrderException {
        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        if (customer != null)
            offerService.paymentFromAccountCredit(id, customer);
        if (loginCustomer != null)
            offerService.paymentFromAccountCredit(id, loginCustomer);
        return "customerHomePage";
    }

    @GetMapping("/onlinePayment/{id}")
    public ModelAndView onlinePayment(@PathVariable("id") Integer id, Model model
    ) throws NotFoundOrderException {

        OrderDto orderDto = orderService.findById(id);
        model.addAttribute("order", orderDto);
        return new ModelAndView("onlinePaymentPage", "onlinePayment", new CreditCardInfo());
    }

    @PostMapping("/onlinePayment")
    public String onlinePayment(HttpServletRequest request,
                                @ModelAttribute("onlinePayment") @Valid CreditCardInfo info, Model model) throws NotFoundCustomerException {
        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        OrderDto orderDto = (OrderDto) session.getAttribute("order");
        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");
        if (customer != null && captcha.equals(verifyCaptcha)) {
            model.addAttribute("onlinePayment", info);
            offerService.onlinePayment(orderDto);
        } else if (loginCustomer != null && captcha.equals(verifyCaptcha)) {
            model.addAttribute("onlinePayment", info);
            offerService.onlinePayment(orderDto);
        } else {
            model.addAttribute("error", "Captcha Invalid");
            return "onlinePaymentPage";
        }
        return "customerHomePage";
    }

    @ExceptionHandler(value = NotFoundOrderException.class)
    public String handleNotFoundOrder(Exception e,Model model){
        model.addAttribute("emptyOrderList",e.getLocalizedMessage());
        return "customerHomePage";

    }
}
