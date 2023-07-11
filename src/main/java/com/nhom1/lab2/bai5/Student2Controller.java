package com.nhom1.lab2.bai5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom1.lab2.bean.Student;
import com.nhom1.lab2.utils.JSONLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class Student2Controller implements JSONLoader<List<Student>> {

    private List<Student> students;

    @PostConstruct
    public void init(){
        try {
            students = load(filePath -> {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Student>> type = new TypeReference<>() {};
                return mapper.readValue(getClass().getClassLoader().getResourceAsStream(filePath), type);
            }, "static/students.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("students")
    public String list(Model model, @RequestParam("i")Optional<Integer> i){
        model.addAttribute("sv", students.get(i.orElse(0)));
        model.addAttribute("dssv", students);
        return "bai5/list";
    }
}
