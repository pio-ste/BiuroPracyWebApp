package com.biuropracy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping(path = "/")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        return "index";
    }

    @GetMapping(path = "/all/calculator")
    public String calculator() {
        return "/all/calculator/calculatorForm";
    }

    @GetMapping(path = "/user/calculator")
    public String userCalculator() {
        return "/all/calculator/UserCalculator";
    }
}
