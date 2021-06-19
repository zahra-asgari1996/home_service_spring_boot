package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.ManagerDto;
import ir.maktab.service.ManagerService;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundManagerException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(value = "/managerPage")
@SessionAttributes({"correctManager"})
public class ManagerController {
    private final ManagerService managerService;
    private final MessageSource messageSource;

    public ManagerController(ManagerService managerService, MessageSource messageSource) {
        this.managerService = managerService;
        this.messageSource = messageSource;
    }


    @PostMapping("/login")
    public String getSignIn(@ModelAttribute("manager") @Valid ManagerDto managerDto)
            throws NotFoundManagerException, InvalidPassword {

        managerService.loginManager(managerDto);
        return "managerHomePage";
    }


    @ExceptionHandler({NotFoundManagerException.class, InvalidPassword.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("manager", new ManagerDto());
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