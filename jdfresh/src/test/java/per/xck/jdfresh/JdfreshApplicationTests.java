package per.xck.jdfresh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JdfreshApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("testing");
        Map<String,Integer> map = new HashMap<>();
        map.put("牛肉",200);
        map.put("西瓜",100);
        System.out.println(map.toString());
        System.out.println(System.currentTimeMillis());
    }

}
