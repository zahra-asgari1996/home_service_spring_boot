package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.FilterUsersDto;
import ir.maktab.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(value = "user")
public class UserController {
    private final UserService userService;
    private final MessageSource messageSource;


    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @PostMapping(value = "/searchUser")
    public ModelAndView searchUsers(@ModelAttribute("users") FilterUsersDto dto) {
        return new ModelAndView("searchUsersPage", "usersList", userService.filterUsers(dto));

    }

    @GetMapping(value = "/searchUser")
    public String searchUsers(Model model) {
        model.addAttribute("users", new FilterUsersDto());
        return "searchUsersPage";
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
