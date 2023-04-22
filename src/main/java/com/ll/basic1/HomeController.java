package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/home/main")

    @ResponseBody
    public String returnMain() {
        return "main";
    }

    @GetMapping("/home/plus")

    @ResponseBody
    // ?뒤 퀴리스트링 옵션적용
    // defaultValue를 통한 누락 방지
    public int returnPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }

    @GetMapping("/home/boolean")

    @ResponseBody
    public Boolean returnBoolean() {
        return true;
    }

    @GetMapping("/home/IntArray")

    @ResponseBody
    public int[] returnIntArray() {
        int[] arr = new int[]{10, 20, 30};
        return arr;
    }

    @GetMapping("/home/IntList")
    @ResponseBody
    public List<Integer> returnIntList() {
        List<Integer> lit = new ArrayList<>() {{
            add(10);
            add(20);
            add(30);
            add(40);
        }};
        return lit;
    }

    @GetMapping("/home/Map")
    @ResponseBody
    public Map<String, Object> returnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "라인");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(10);
                add(100);
            }});
        }};
        return map;
    }

    @GetMapping("/home/classObj_car")
    @ResponseBody
    public Car returnCar() {
        Car car1 = new Car(1, 100, "라인", new ArrayList<>() {{
            add(2);
            add(10);
            add(100);
        }});
        return car1;
    }
    @GetMapping("/home/classObj_car2")
    @ResponseBody
    public Car_V2 returnCarV2() {
        Car_V2 car = new Car_V2(1, 100, "소나타", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }
}

class Car{
    private final int id;
    private final int speed;
    private final String name;
    private final List<Integer> relatedIds;

    public Car(int id, int speed, String name, List<Integer> relatedIds){
        this.id = id;
        this.speed = speed;
        this.name = name;
        this.relatedIds = relatedIds;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

}
// lombok 기능을 통한 구현 좀더 단순하게 구현 가능
@AllArgsConstructor
@Getter
class Car_V2{

    private final int id;

    private final int speed;
    @Setter
    private  String name;

    private final List<Integer> relatedIds;

}
