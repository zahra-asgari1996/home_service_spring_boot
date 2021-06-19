package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.ServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SubServiceService;
import ir.maktab.service.exception.DuplicatedDataException;
import ir.maktab.service.exception.NotFoundServiceException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("subService")
public class SubServiceController {
    private final SubServiceService subServiceService;
    private final ServiceService service;
    private final MessageSource messageSource;

    public SubServiceController(SubServiceService subServiceService, ServiceService service, MessageSource messageSource) {
        this.subServiceService = subServiceService;
        this.service = service;
        this.messageSource = messageSource;
    }

    public void saveNewSubService(SubServiceDto subServiceDto) throws DuplicatedDataException, NotFoundServiceException {
        subServiceService.saveNewSubService(subServiceDto);
    }

    @GetMapping("/addNewSubService")
    public String addNewSubService(Model model) {
        model.addAttribute("newSubService", new SubServiceDto());
        model.addAttribute("serviceList", service.fetchAllServices());
        return "createNewSubServicePage";
    }

    @PostMapping("/addNewSubService")
    public String addNewSubService(@ModelAttribute("newSubService") @Valid SubServiceDto dto) throws DuplicatedDataException,
            NotFoundServiceException {
        System.out.println(dto.getService().getName());
        subServiceService.saveNewSubService(dto);
        return "managerHomePage";

    }

    @GetMapping("/getSubService")
    public String getSubServices(@RequestParam("service") String service, Model model,
                                 @SessionAttribute("serviceList") List<ServiceDto> serviceList
            , @SessionAttribute("newOrder") OrderDto dto,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<String> subServices = subServiceService.getSubServicesByServiceName(service);
        model.addAttribute("newOrder", dto);
        model.addAttribute("subServiceList", subServices);
        model.addAttribute("serviceList", serviceList);
        model.addAttribute("selectedService", service);
        return "createNewOrderPage";
    }

    @ExceptionHandler({DuplicatedDataException.class, NotFoundServiceException.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("newOffer", new OfferDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
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
