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
@RequestMapping("/comment")
@SessionAttributes("comment")
public class CommentController {

    private final CommentService commentService;
    private final ExpertService expertService;

    public CommentController(CommentService commentService, ExpertService expertService) {
        this.commentService = commentService;
        this.expertService = expertService;
    }

    @GetMapping("/addComment/{id}")
    public ModelAndView addComment(@PathVariable("id") Integer id) {
        CommentDto commentDto = new CommentDto();
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        commentDto.setOrderDto(orderDto);
        return new ModelAndView("createNewCommentPage", "comment", commentDto);
    }


    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("comment") @Valid CommentDto dto)
            throws NotFoundOrderException {

        commentService.saveNewComment(dto);
        return "customerHomePage";
    }


    @GetMapping("/showRate")
    public String showRate(HttpServletRequest request, Model model)
            throws NotFoundExpertException {

        HttpSession session = request.getSession(false);
        ExpertDto expert = (ExpertDto) session.getAttribute("expert");
        ExpertDto loginExpert = (ExpertDto) session.getAttribute("loginExpert");
        if (expert != null) {
            model.addAttribute("commentList", commentService.findByExpert(expert));
            model.addAttribute("rate", expertService.showAvgRate(expert));
        }
        if (loginExpert != null) {
            model.addAttribute("commentList", commentService.findByExpert(loginExpert));
            model.addAttribute("rate", expertService.showAvgRate(loginExpert));
        }
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
}

