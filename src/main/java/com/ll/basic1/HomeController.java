package com.ll.basic1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {
    private List<Person> people;

    public HomeController() {
        people = new ArrayList<>();
    }

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

    @GetMapping("/home/car_v1_Map")
    @ResponseBody
    public Map<String, Object> returnCarV1() {
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

    @GetMapping("/home/car_v2_Class")
    @ResponseBody
    public Car returnCarV2() {
        Car car1 = new Car(1, 100, "라인", new ArrayList<>() {{
            add(2);
            add(10);
            add(100);
        }});
        return car1;
    }

    @GetMapping("/home/car_v3_Class")
    @ResponseBody
    public Car_V2 returnCarV3() {
        Car_V2 car = new Car_V2(1, 100, "소나타", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/car_v4_ListMap")
    @ResponseBody
    public List<Map<String, Object>> returnCarV4() {
        Map<String, Object> car1 = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "소나타");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);

            }});
        }};
        Map<String, Object> car2 = new LinkedHashMap<>() {{
            put("id", 2);
            put("speed", 200);
            put("name", "그랜저");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};
//        List<Map<String, Object>> listMap = new ArrayList<>();
//        listMap.add(car1);
//        listMap.add(car2);
        List<Map<String, Object>> listMap = new ArrayList<>() {{
            add(car1);
            add(car2);
        }};
        return listMap;
    }

    @GetMapping("/home/car_v5_List")
    @ResponseBody
    public List<Car_V2> returnCarV5() {
        Car_V2 car1 = new Car_V2(1, 111, "소나타", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});
        Car_V2 car2 = new Car_V2(2, 222, "그랜저", new ArrayList<>() {{
            add(5);
            add(6);
            add(7);
        }});

        List<Car_V2> list_car = new ArrayList<>() {{
            add(car1);
            add(car2);
        }};
        return list_car;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age) {
        Person p = new Person(name, age);
        //입력 결과 확인시 @ToString 추가
        System.out.println(p);
        people.add(p);
        return "%d번 사람이 추가되었습니다".formatted(p.getId());
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id) {
        boolean removed = people.removeIf(person -> person.getId() == id);
        if (removed == false) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }
        return "%d번 사람이 제거되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age) {
        Person found = people
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (found == null) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }
        found.setId(id);
        found.setName(name);
        found.setAge(age);
        return "%d번 사람이 수정되었습니다.".formatted(id);
    }


    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> returnPeople() {
        return people;
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    // 각각의 브라우저를 구분하는 방식 / 쿠폰지급 / 값은 브라우저에만 기록된 형식

    // req 받은편지 resp 보낼편지
        public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.
        int countInCookie =0;

        if(req.getCookies() != null){
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        int newCountInCookie = countInCookie + 1;
        resp.addCookie(new Cookie("count", newCountInCookie+""));
        return newCountInCookie;
    }
}

class Car {
    private final int id;
    private final int speed;
    private final String name;
    private final List<Integer> relatedIds;

    public Car(int id, int speed, String name, List<Integer> relatedIds) {
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
@Getter// class에 포함된 모든 인자 getter추가
class Car_V2 {
    private final int id;
    private final int speed;
    @Setter// 특정 인자 setter추가
    private String name;
    private final List<Integer> relatedIds;

}

@AllArgsConstructor
@Getter
@ToString
class Person {
    private static int lastId;
    @Setter
    private int id;
    @Setter
    private String name;
    @Setter
    private int age;

    static {
        lastId = 0;
    }

    //    @AllArgsConstructor로 대체
//    public Person(int id, String name, int age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//}
//    특정 생성자 필요시 제작필요
    Person(String name, int age) {
        this(++lastId, name, age);
    }
}
