package com.nhom1.lab2.bai2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom1.lab2.bean.Student;
import com.nhom1.lab2.utils.JSONLoader;
import com.nhom1.lab2.utils.Loader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController implements JSONLoader<Student> {

    private Student student;

    @PostConstruct
    public void init(){
        try {
            student = load((filePath) -> {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(getClass().getClassLoader().getResourceAsStream(filePath), Student.class);
            }, "static/student.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("home/index")
    public String index(Model model) {
        model.addAttribute("msg", "Welcome to Thymeleaf");
        if(student != null)
            model.addAttribute("sv", student);
        return "bai2/home/index";
    }
}
