package com.nhom1.lab2.bai1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("msg", "FPT <b>Polytechnic</b>");
        return "bai1/hello";
    }
}
