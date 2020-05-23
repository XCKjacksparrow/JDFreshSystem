package per.xck.jdfresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.xck.jdfresh.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
}
