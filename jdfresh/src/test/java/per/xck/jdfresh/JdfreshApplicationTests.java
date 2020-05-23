package per.xck.jdfresh;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.xck.jdfresh.entity.Orders;
import per.xck.jdfresh.repository.OrdersRepository;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JdfreshApplicationTests {

    @Autowired
    OrdersRepository ordersRepository;
    @Test
    void contextLoads() {
        Orders orders = new Orders();
        orders.setStatus("已收货");
        orders.setUsername("kazemi");
        orders.setDeliverier("random");
        orders.setAddress("江苏无锡");
        orders.setOrderTime(System.currentTimeMillis());
        orders.setReceiptTime(System.currentTimeMillis());
        System.out.println("testing");
        Map<String,Integer> map = new HashMap<>();
        map.put("牛肉",200);
        map.put("西瓜",100);
//        System.out.println(map.toString());
        orders.setContent(map.toString());
        System.out.println(orders);
        ordersRepository.save(orders);
//        System.out.println(System.currentTimeMillis());
    }

}
