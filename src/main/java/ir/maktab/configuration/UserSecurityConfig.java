package ir.maktab.configuration;

import ir.maktab.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationSuccessHandler successHandler;

    public UserSecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, AuthenticationSuccessHandler successHandler) {
        super();
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.successHandler = successHandler;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/userLogin", "/customer/register", "/expert/register","/loginFailed")
                .permitAll()
                .anyRequest()
                .hasAnyRole("CUSTOMER","EXPERT")

                .and()
                .formLogin()
                .loginPage("/userLogin")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler((request, response, exception) -> {
                    String email = request.getParameter("email");
                    String error = exception.getMessage();
                    System.out.println("A failed login attempt with email: "
                            + email + ". Reason: " + error);
                    String redirectUrl = request.getContextPath() + "/loginFailed";
                    response.sendRedirect(redirectUrl);
                })

                .loginProcessingUrl("/userLogin")

                .and()
                .logout()
                .logoutUrl("/userLogout")
                .logoutSuccessUrl("/")
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDeniedPage.jsp");

    }


}

