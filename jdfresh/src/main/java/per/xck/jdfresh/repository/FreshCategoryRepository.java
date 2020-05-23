package per.xck.jdfresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.xck.jdfresh.entity.FreshCategory;

public interface FreshCategoryRepository extends JpaRepository<FreshCategory,Integer> {
}
