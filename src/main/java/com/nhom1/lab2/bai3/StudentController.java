package com.nhom1.lab2.bai3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom1.lab2.bean.Student;
import com.nhom1.lab2.utils.JSONLoader;
import com.nhom1.lab2.utils.Loader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController implements JSONLoader<List<Student>> {

    private List<Student> students;

    @PostConstruct
    public void init(){
        try {
            TypeReference<List<Student>> studentType = new TypeReference<>(){};
            students = load(filePath -> {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(getClass().getClassLoader().getResourceAsStream(filePath), studentType);
            }, "static/students.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("student")
    public String student(Model model,
                          @RequestParam("i")Optional<Integer> i)
    {
        if(students != null) {
            int index = i.orElse(0);
            index = index >= students.size() ? students.size()-1 : index;
            Student student = students.get(index);
            model.addAttribute("sv", student);
            model.addAttribute("i", index);
        }
        return "bai3/student";
    }

    @ExceptionHandler
    public String exception(Exception ex, Model model) {
        model.addAttribute("error", "Invalid value");
        return "bai3/student";
    }
}
