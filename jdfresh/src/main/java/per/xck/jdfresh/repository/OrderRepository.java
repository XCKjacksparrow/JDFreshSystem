package per.xck.jdfresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.xck.jdfresh.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
