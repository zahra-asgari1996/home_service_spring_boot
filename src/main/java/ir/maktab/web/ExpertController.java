package ir.maktab.web;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServiceService;
import ir.maktab.service.exception.*;
import ir.maktab.service.validation.LoginValidation;
import ir.maktab.service.validation.RegisterValidation;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Controller
@RequestMapping(value = "/expert")
public class ExpertController {
    private final ExpertService expertService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;
    private final MessageSource messageSource;

    public ExpertController(ExpertService expertService, SubServiceService subServiceService, OrderService orderService, MessageSource messageSource) {
        this.expertService = expertService;
        this.subServiceService = subServiceService;
        this.orderService = orderService;
        this.messageSource = messageSource;
    }

    @GetMapping("/expertHomePage")
    public String goToHomePage(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.setAttribute("expert",getUser());
        return "expertHomePage";
    }
    @GetMapping("/balance")
    @PreAuthorize("hasRole('EXPERT')")
    public String getBalance(Model model){
        Double balance = expertService.getBalance(getUser());
        model.addAttribute("balance",balance);
        return "expertHomePage";
    }


    @GetMapping("/register")
    public ModelAndView goToExpertRegisterPage() {
        return new ModelAndView("expertRegisterPage", "expert", new ExpertDto());
    }

    @PostMapping(value = "/register")
    public String registerExpert(@ModelAttribute("expert") @Validated(RegisterValidation.class) ExpertDto expertDto,
                                 Model model,HttpServletRequest request)
            throws DuplicatedEmailAddressException, UnsupportedEncodingException, MessagingException {
        ExpertDto expert = expertService.registerExpert(expertDto,getSiteURL(request));
        model.addAttribute("credit",expert.getCredit());
        return "register_success";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (expertService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/changePassword")
    @PreAuthorize("hasRole('EXPERT')")
    public String changePassword(Model model) {
        model.addAttribute("changePassword", new ExpertDto());
        return "expertPassChange";
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('EXPERT')")
    public String changePassword(Model model, @ModelAttribute("changePassword") ExpertDto dto,HttpServletRequest request) {
        dto.setEmail(getUser().getEmail());
        expertService.changePassword(dto);
        model.addAttribute("successAlert",messageSource.getMessage("password.changed",null,new Locale("en_us")));
        return "expertHomePage";
    }

    @GetMapping("/selectField")
    @PreAuthorize("hasRole('EXPERT')")
    public String selectField(Model model) {
        model.addAttribute("listOfFields", subServiceService.fetchAllSubServices());
        return "selectFieldForExpert";
    }


    @GetMapping("/selectField/{id}")
    @PreAuthorize("hasRole('EXPERT')")
    public String selectField(Model model, @PathVariable("id") Integer id,HttpServletRequest request)
            throws NotFoundExpertException,
            NotFoundSubServiceException {

        SubServiceDto subServiceDto = new SubServiceDto();
        subServiceDto.setId(id);
        expertService.addExpertToSubService(subServiceDto, getUser());
        model.addAttribute("successAlert",messageSource.getMessage("field.select",null,new Locale("en_us")));
        return "expertHomePage";
    }

    @GetMapping("/showOrders")
    @PreAuthorize("hasRole('EXPERT')")
    public String showOrders(HttpServletRequest request, Model model) throws AccessException,
            NotFoundOrderForExpertException {
        model.addAttribute("ordersList", orderService.findOrdersBaseOnExpertSubServicesAndSituation(getUser()));
        return "showOrdersForExpertToSendOffer";
    }

    @GetMapping("/showOrdersToClickEndOfWork")
    @PreAuthorize("hasRole('EXPERT')")
    public String showSuggestion(Model model, HttpServletRequest request)
            throws NotFoundOrderException {
        model.addAttribute("ordersList", orderService.findByExpert(getUser()));
        return "showOrdersForExpertToEndOfWork";
    }

    @ExceptionHandler(value = AccessException.class)
    public String accessException(Exception e,Model model){
        model.addAttribute("accessException",e.getLocalizedMessage());
        return "expertHomePage";
    }

    @ExceptionHandler(value = {NotFoundOrderForExpertException.class})
    public String notFoundOrderForExpertException(Exception e,Model model){
        model.addAttribute("notFoundOrder",e.getLocalizedMessage());
        return "expertHomePage";
    }

    public ExpertDto getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        ExpertDto dto = new ExpertDto();
        dto.setEmail(userName);
        return dto;
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
