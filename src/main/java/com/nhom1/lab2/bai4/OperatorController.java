package com.nhom1.lab2.bai4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperatorController {
    @GetMapping("demo/operator")
    public String demo(Model model) {
        model.addAttribute("x", 5);
        model.addAttribute("y", 7);
        return "bai4/operator";
    }

    @ExceptionHandler
    public String error(Exception exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "bai4/operator";
    }
}
