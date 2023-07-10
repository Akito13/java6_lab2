package com.nhom1.lab2.bai3;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScopeController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private ServletContext app;

    @GetMapping("scope")
    public String hello(Model model) {
        model.addAttribute("a", "In model");
        request.setAttribute("b", "In request");
        session.setAttribute("c", "In session");
        app.setAttribute("d", "In servlet context");
        return "bai3/scope";
    }
}
