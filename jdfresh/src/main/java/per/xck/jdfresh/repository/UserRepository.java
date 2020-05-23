package per.xck.jdfresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.xck.jdfresh.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
