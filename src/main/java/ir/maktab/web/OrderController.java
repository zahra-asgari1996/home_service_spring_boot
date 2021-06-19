package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.exception.NotFoundCustomerException;
import ir.maktab.service.exception.NotFoundOrderException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/order")
@SessionAttributes({"newOrder"})
public class OrderController {
    private final ServiceService service;
    private final OrderService orderService;
    private final MessageSource messageSource;


    public OrderController(ServiceService service, OrderService orderService, MessageSource messageSource) {
        this.service = service;
        this.orderService = orderService;
        this.messageSource = messageSource;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/createOrder")
    public String createOrder(Model model, HttpServletRequest request) {
        model.addAttribute("newOrder", new OrderDto());
        model.addAttribute("serviceList", service.fetchAllServices());
        model.addAttribute("selectedService", "select");
        HttpSession session = request.getSession();
        session.setAttribute("serviceList", service.fetchAllServices());
        session.setAttribute("newOrder", model.getAttribute("newOrder"));
        return "createNewOrderPage";
    }

    @PostMapping("/createOrder")
    public String createNewOrder(@ModelAttribute("newOrder") @Valid OrderDto dto, HttpServletRequest request)
            throws NotFoundCustomerException {
        HttpSession session = request.getSession(false);
        CustomerDto customer = (CustomerDto) session.getAttribute("customer");
        CustomerDto loginCustomer = (CustomerDto) session.getAttribute("loginCustomer");
        CustomerDto customerDto = new CustomerDto();
        if (customer != null)
            customerDto.setEmail(customer.getEmail());
        if (loginCustomer != null)
            customerDto.setEmail(loginCustomer.getEmail());
        dto.setCustomer(customerDto);
        orderService.saveNewOrder(dto);
        return "customerHomePage";
    }

    @GetMapping("/endOfWork/{id}")
    public String endOfWork(@PathVariable("id") Integer id) throws NotFoundOrderException {
        orderService.endOfWork(id);
        return "expertHomePage";
    }

    @GetMapping("/confirmPay/{id}")
    public String ConfirmPay(@PathVariable("id") Integer id) throws NotFoundOrderException {
        orderService.confirmPay(id);
        return "expertHomePage";
    }

    @GetMapping("/startWork/{id}")
    public String startWork(@PathVariable("id") Integer id) throws NotFoundOrderException {
        orderService.startWork(id);
        return "expertHomePage";
    }


    @ExceptionHandler({NotFoundCustomerException.class, NotFoundOrderException.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("newOffer", new OfferDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        System.out.println(lastView);
        return new ModelAndView(lastView, model);
    }


    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        ex.getFieldErrors().forEach(
                error -> model.put(error.getField(),
                        messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()), null, new Locale("fa_ir")))
        );
        return new ModelAndView(lastView, model);
    }


}
