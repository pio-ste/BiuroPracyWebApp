package com.biuropracy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        return "index";
    }
}
