package com.nhom1.lab2.bai6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom1.lab2.bean.Student;
import com.nhom1.lab2.utils.JSONLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class UtilityController implements JSONLoader<List<Student>> {

    private List<Student> students;

    @PostConstruct
    public void init(){
        try {
            students = load(filePath -> {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Student>> ref = new TypeReference<>() {};
                return mapper.readValue(getClass().getClassLoader().getResourceAsStream(filePath), ref);
            }, "static/students.json");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @GetMapping("demo/utilities")
    public String utilities(Model model) {
        model.addAttribute("dssv", students);
        model.addAttribute("now",
            Date.from(
                    LocalDate.now()
                            .atTime(LocalTime.now())
                            .atZone(ZoneId.systemDefault())
                            .toInstant()
            )
        );
        return "bai6/utilities";
    }
}
