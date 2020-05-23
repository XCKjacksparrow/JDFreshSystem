package per.xck.jdfresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import per.xck.jdfresh.entity.Fresh;

import java.util.List;

public interface FreshRepository extends JpaRepository<Fresh,Integer> {
    @Query(value = "select * from fresh where fresh_category = ?1",nativeQuery = true)
    List<Fresh> getAllFreshByFreshCategory(@Param("freshCategory") String freshCategory);
}
