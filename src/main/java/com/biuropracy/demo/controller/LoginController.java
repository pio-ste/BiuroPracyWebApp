package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.User;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user" , user);
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("succesMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("succesMessage", "Uzytkownik o podanych danych już istnieje.");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("succesMessage", "Konto zostało utworzone pomyślnie.");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
    public ModelAndView homeUser() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/user/userHome.html");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/employeeHome", method = RequestMethod.GET)
    public ModelAndView homeEmployee() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/employee/employeeHome.html");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
    public ModelAndView homeAdmin() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/admin/adminHome.html");
        return modelAndView;
    }

}
