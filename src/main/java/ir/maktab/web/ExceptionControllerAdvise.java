package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.ManagerDto;
import ir.maktab.service.exception.*;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvise {
    private final static Logger logger=Logger.getLogger(ExceptionControllerAdvise.class);
    private final MessageSource messageSource;

    public ExceptionControllerAdvise(MessageSource messageSource) {
        this.messageSource = messageSource;
    }



    @ExceptionHandler(value = NotEnoughAccountBalance.class)
    public String notEnoughBalanceException(Model model,Exception e){
        logger.info("Not enough account balance...");
        model.addAttribute("error", e.getLocalizedMessage());
        return "customerHomePage";
    }

    @ExceptionHandler(value = NotFoundOrderException.class)
    public ModelAndView showOrdersException(Exception e,HttpServletRequest request){
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        logger.info("Not found order...");
        return new ModelAndView(lastView, "error",e.getLocalizedMessage());
    }

    @ExceptionHandler(value = NotFoundSubServiceException.class)
    public ModelAndView NotFoundSubServiceException(Exception e,HttpServletRequest request){
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        logger.info("Not found order...");
        return new ModelAndView(lastView, "error",e.getLocalizedMessage());
    }

    @ExceptionHandler(value = NotFoundCustomerException.class)
    public ModelAndView loginCustomerException(Exception e,HttpServletRequest request){
        Map<String, Object> model = new HashMap<>();
        logger.info("Customer not found...");
        model.put("error", e.getLocalizedMessage());
        model.put("loginCustomer", new CustomerDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotFoundExpertException.class)
    public ModelAndView loginExpertException(Exception e,HttpServletRequest request){
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("loginExpert", new CustomerDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = InvalidPassword.class)
    public ModelAndView invalidPass(Exception e,HttpServletRequest request){
        Map<String, Object> model = new HashMap<>();
        logger.info("Password is incorrect...");
        model.put("error", e.getLocalizedMessage());
        model.put("loginCustomer", new CustomerDto());
        model.put("loginExpert",new ExpertDto());
        model.put("manager", new ManagerDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }


    @ExceptionHandler(value = {DuplicatedEmailAddressException.class})
    public ModelAndView registerCustomerException(Exception e,HttpServletRequest request){
        Map<String, Object> model = new HashMap<>();
        logger.info("Email is duplicate...");
        model.put("error", e.getLocalizedMessage());
        model.put("customer", new CustomerDto());
        model.put("expert",new ExpertDto());
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler({NotFoundManagerException.class})
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getLocalizedMessage());
        model.put("manager", new ManagerDto());
        logger.info("Manager not found...");
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, model);
    }




}
