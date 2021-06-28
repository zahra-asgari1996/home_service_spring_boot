package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.ServiceDto;
import ir.maktab.service.ServiceService;
import ir.maktab.service.exception.DuplicatedDataException;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    private final ServiceService service;
    private final MessageSource messageSource;


    public ServiceController(ServiceService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }


    @GetMapping(value = "/addNewService")
    @PreAuthorize("hasRole('MANAGER')")
    public String addNewService(Model model) {
        model.addAttribute("newService", new ServiceDto());
        return "createNewServicePage";
    }

    @PostMapping(value = "/addNewService")
    @PreAuthorize("hasRole('MANAGER')")
    public String addNewService(@ModelAttribute("newService") @Valid ServiceDto serviceDto,Model model) throws DuplicatedDataException {
        service.saveNewService(serviceDto);
        model.addAttribute("successAlert",messageSource.getMessage("add.new.service",null,new Locale("en_us")));
        return "managerHomePage";
    }

    @ExceptionHandler({DuplicatedDataException.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("newService", new ServiceDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }

}
