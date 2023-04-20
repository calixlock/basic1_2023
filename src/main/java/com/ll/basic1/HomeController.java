package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home/main")

    @ResponseBody
    public String showMain(){
        return "main";
    }
    @GetMapping("/home/main2")

    @ResponseBody
    public String showMain2(){
        return "main2 ";
    }
    @GetMapping("/home/main3")

    @ResponseBody
    public String showMain3(){
        return "main3 ";
    }
}
