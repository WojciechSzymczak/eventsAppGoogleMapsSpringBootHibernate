package com.events.eventsapp.controller;

import com.events.eventsapp.configuration.RefererRedirectionAuthenticationSuccessHandler;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.UserRepository;
import com.events.eventsapp.service.UserService;
import com.events.eventsapp.service.UserServiceImpl;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

@Controller //TODO
public class LoginController {

    @Qualifier("userService")
    @Autowired
    UserService userService = new UserServiceImpl();

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login_get(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;

    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
//
    public ModelAndView loginProcess(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String password,
                                    @RequestParam(required = false) String requestedPath) { //, @ModelAttribute("login") Login login

        ModelAndView mav;

        UserModel user = null;

        try {

            user = userService.validateUser(email, password);

        } catch(Exception e) {

            System.out.print(e.getMessage());

        }

        System.out.print("\n\n\n " + email + "\n" + password);

        if (null != user) {

            System.out.println("User logged in.");



                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    //anonymous inner type
                    public String getAuthority() {
                        return "USER";
                    }
                };

                grantedAuthorities.add(grantedAuthority);



//            Authentication auth =
//                    new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
//
//            SecurityContextHolder.getContext().setAuthentication(auth);


            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(auth);

            mav = new ModelAndView("/");

            mav.addObject("name", user.getName());
            //mav.addObject("userRole", user.get)


        } else {


            System.out.println("Login error");

            mav = new ModelAndView("login");

            mav.addObject("message", "Username or Password is wrong!!");

        }

        return mav;

    }

}
