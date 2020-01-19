package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.service.EmployerService;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    EmployerService employerService;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user" , user);
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("succesMessage", "Uzytkownik o podanych danych już istnieje.");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Konto zostało utworzone pomyślnie.");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @GetMapping(value = "/user/userHome")
    public ModelAndView homeUser() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/user/userHome.html");
        return modelAndView;
    }

    @GetMapping(value = "/employer/employerHome")
    public ModelAndView homeEmployee() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/employer/employerHome.html");
        return modelAndView;
    }

    @GetMapping(value = "/admin/adminHome")
    public ModelAndView homeAdmin() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/admin/adminHome.html");
        return modelAndView;
    }

}
