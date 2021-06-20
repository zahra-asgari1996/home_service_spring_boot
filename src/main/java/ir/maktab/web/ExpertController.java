package ir.maktab.web;

import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServiceService;
import ir.maktab.service.exception.*;
import ir.maktab.service.validation.LoginValidation;
import ir.maktab.service.validation.RegisterValidation;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/expert")
@SessionAttributes({"expert", "loginExpert"})
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

    @GetMapping("/register")
    public ModelAndView goToExpertRegisterPage() {
        return new ModelAndView("expertRegisterPage", "expert", new ExpertDto());
    }


    @PostMapping(value = "/register")
    public String save(@ModelAttribute("expert") @Validated(RegisterValidation.class) ExpertDto expertDto,Model model)
            throws DuplicatedEmailAddressException {
        ExpertDto expert = expertService.saveNewExpert(expertDto);
        model.addAttribute("credit",expert.getCredit());
        return "expertHomePage";
    }


    @GetMapping("/login")
    public ModelAndView goToLoginExpertPage() {
        return new ModelAndView("expertLoginPage", "loginExpert", new ExpertDto());
    }

    @PostMapping("/login")
    public String loginExpert(@ModelAttribute("loginExpert") @Validated(LoginValidation.class) ExpertDto dto,Model model)
            throws NotFoundExpertException, InvalidPassword {
        ExpertDto expert = expertService.loginExpert(dto);
        model.addAttribute("credit",expert.getCredit());
        return "expertHomePage";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePassword", new ExpertDto());
        return "expertPassChange";
    }

    @PostMapping("/changePassword")
    public String changePassword(HttpServletRequest request, @ModelAttribute("changePassword") ExpertDto dto) {
        HttpSession session = request.getSession(false);
        ExpertDto expert = (ExpertDto) session.getAttribute("expert");
        ExpertDto loginExpert = (ExpertDto) session.getAttribute("loginExpert");
        if (expert != null) {
            dto.setEmail(expert.getEmail());
            expertService.changePassword(dto);
        }
        if (loginExpert != null) {
            dto.setEmail(loginExpert.getEmail());
            expertService.changePassword(dto);
        }
        return "expertHomePage";
    }


    @GetMapping("/selectField")
    public String selectField(Model model) {
        model.addAttribute("listOfFields", subServiceService.fetchAllSubServices());
        return "selectFieldForExpert";
    }


    @GetMapping("/selectField/{id}")
    public String selectField(HttpServletRequest request, @PathVariable("id") Integer id)
            throws NotFoundExpertException,
            NotFoundSubServiceException {

        HttpSession session = request.getSession(false);
        ExpertDto expert = (ExpertDto) session.getAttribute("expert");
        ExpertDto loginExpert = (ExpertDto) session.getAttribute("loginExpert");
        SubServiceDto subServiceDto = new SubServiceDto();
        subServiceDto.setId(id);
        if (expert != null) {
            expertService.addExpertToSubService(subServiceDto, expert);
        }
        if (loginExpert != null) {
            expertService.addExpertToSubService(subServiceDto, loginExpert);
        }
        return "expertHomePage";
    }

    @GetMapping("/showOrders")
    public String showOrders(HttpServletRequest request, Model model) throws NotFoundOrderException {
        HttpSession session = request.getSession(false);
        ExpertDto expert = (ExpertDto) session.getAttribute("expert");
        ExpertDto loginExpert = (ExpertDto) session.getAttribute("loginExpert");
        if (expert != null) {
            model.addAttribute("ordersList", orderService.findOrdersBaseOnExpertSubServicesAndSituation(expert));
        }
        if (loginExpert != null) {
            model.addAttribute("ordersList", orderService.findOrdersBaseOnExpertSubServicesAndSituation(loginExpert));
        }
        return "showOrdersForExpertToSendOffer";
    }

    @GetMapping("/showOrdersToClickEndOfWork")
    public String showSuggestion(Model model, HttpServletRequest request)
            throws NotFoundOrderException {

        HttpSession session = request.getSession(false);
        ExpertDto expert = (ExpertDto) session.getAttribute("expert");
        ExpertDto loginExpert = (ExpertDto) session.getAttribute("loginExpert");
        if (expert != null) {
            model.addAttribute("orderList", orderService.findByExpert(expert));
        }
        if (loginExpert != null) {
            model.addAttribute("ordersList", orderService.findByExpert(loginExpert));
        }
        return "showOrdersForExpertToEndOfWork";
    }
}
