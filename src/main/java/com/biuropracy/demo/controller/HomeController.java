package com.biuropracy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    /**
     * strona główna
     * @return
     */
    @GetMapping(path = "/")
    public String hello() {
        return "index";
    }

    /**
     * wyświetlanie kalkulatora wynagrodzeń dla użytkowników
     * @param model
     * @param salary
     * @return
     */
    @GetMapping(path = "/all/calculator/{salary}")
    public String calculator(Model model, @PathVariable("salary") Integer salary) {
        model.addAttribute("salary", salary);
        return "/all/calculator/calculatorForm";
    }
    @GetMapping(path = "/user/calculator/{salary}")
    public String userCalculator(Model model, @PathVariable("salary") Integer salary) {
        model.addAttribute("salary", salary);
        return "/all/calculator/UserCalculator";
    }

    @GetMapping(path = "/admin/calculator/{salary}")
    public String adminCalculator(Model model, @PathVariable("salary") Integer salary) {
        model.addAttribute("salary", salary);
        return "/all/calculator/adminCalculator";
    }

    @GetMapping(path = "/employer/calculator/{salary}")
    public String employerCalculator(Model model, @PathVariable("salary") Integer salary) {
        model.addAttribute("salary", salary);
        return "/all/calculator/employerCalculator";
    }
}
