package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home/main")

    @ResponseBody
    public String showMain(){
        return "main";
    }
    @GetMapping("/home/plus")

    @ResponseBody
    // ?뒤 퀴리스트링 옵션적용
    // defaultValue를 통한 누락 방지
    public int showPlus(@RequestParam(defaultValue = "0") int a,@RequestParam(defaultValue = "0") int b){
        return a+b;
    }
}
