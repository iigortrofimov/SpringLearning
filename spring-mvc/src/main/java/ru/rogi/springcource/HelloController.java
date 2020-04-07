package ru.rogi.springcource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        //вернет представление (View)
        return "hello";
    }
}
