package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.UserService;
import ir.maktab.service.exception.NotFoundExpertException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping(value = "/confirmUser/{id}")
    public String confirmUser(Model model,@PathVariable("id") Integer id) throws NotFoundExpertException {
        UserDto confirmUser = userService.confirmUser(id);
        model.addAttribute("confirmUser",confirmUser);
        return "managerHomePage";
    }

    @ExceptionHandler(value = NotFoundExpertException.class)
    public String handleException(Exception e, Model model, HttpServletRequest request){
        model.addAttribute("error",e.getLocalizedMessage());
        String lastView= (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return lastView;
    }
}
