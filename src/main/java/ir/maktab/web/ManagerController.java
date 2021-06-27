package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.FilterOrdersDto;
import ir.maktab.dto.FilterSpecialUserOrdersDto;
import ir.maktab.dto.ManagerDto;
import ir.maktab.service.ManagerService;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SubServiceService;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundManagerException;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/managerPage")
@SessionAttributes({"correctManager"})
public class ManagerController {
    private final ManagerService managerService;
    private final MessageSource messageSource;
    private final SubServiceService subServiceService;
    private final ServiceService serviceService;
    private final OrderService orderService;

    public ManagerController(ManagerService managerService, MessageSource messageSource, SubServiceService subServiceService, ServiceService serviceService, OrderService orderService) {
        this.managerService = managerService;
        this.messageSource = messageSource;
        this.subServiceService = subServiceService;
        this.serviceService = serviceService;
        this.orderService = orderService;
    }

    @GetMapping ("/managerHomePage")
    @PreAuthorize("hasRole('MANAGER')")
    public String goToHome(){
        return "managerHomePage";
    }


    @PostMapping("/login")
    @PreAuthorize("hasRole('MANAGER')")
    public String getSignIn(@ModelAttribute("manager") @Valid ManagerDto managerDto,HttpServletRequest request)
            throws NotFoundManagerException, InvalidPassword {
        HttpSession session = request.getSession(false);
        session.setAttribute("manager",managerDto);
        return "managerHomePage";
    }
    @GetMapping(value = "/orderHistoryPage")
    public String goToSearchInOrdersPage(Model model){
        model.addAttribute("orderHistoryList",new FilterOrdersDto());
        model.addAttribute("subServiceList",subServiceService.fetchAllSubServices());
        model.addAttribute("serviceList",serviceService.fetchAllServices());
        model.addAttribute("situationList",orderService.situations());
        return "filterOrders";
    }

    @GetMapping(value = "/filterUserBaseOnNumOfOrders")
    public String goToFilterPage(){
        return "filterUsersBaseOnNumOfOrders";
    }





    @ExceptionHandler({NotFoundManagerException.class, InvalidPassword.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("manager", new ManagerDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }
}
