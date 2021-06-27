package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.CommentDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.LoginCustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.CommentService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundOrderException;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/comment")
@SessionAttributes("comment")
public class CommentController {

    private final CommentService commentService;
    private final ExpertService expertService;
    private final MessageSource messageSource;

    public CommentController(CommentService commentService, ExpertService expertService, MessageSource messageSource) {
        this.commentService = commentService;
        this.expertService = expertService;
        this.messageSource = messageSource;
    }

    @GetMapping("/addComment/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView addComment(@PathVariable("id") Integer id) {
        CommentDto commentDto = new CommentDto();
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        commentDto.setOrderDto(orderDto);
        return new ModelAndView("createNewCommentPage", "comment", commentDto);
    }


    @PostMapping("/addComment")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String addComment(@ModelAttribute("comment") @Valid CommentDto dto,Model model)
            throws NotFoundOrderException {

        commentService.saveNewComment(dto);
        model.addAttribute("successAlert",messageSource.getMessage("comment.added",null,new Locale("en_us")));
        return "customerHomePage";
    }


    @GetMapping("/showRate")
    @PreAuthorize("hasRole('EXPERT')")
    public String showRate( Model model)
            throws NotFoundExpertException {
        model.addAttribute("commentList", commentService.findByExpert(getUser()));
        model.addAttribute("rate", expertService.showAvgRate(getUser()));
        return "showExpertRate";
    }


    @ExceptionHandler({NotFoundOrderException.class, NotFoundExpertException.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("comment", new LoginCustomerDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        System.out.println(lastView);
        return new ModelAndView(lastView, model);
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
}

