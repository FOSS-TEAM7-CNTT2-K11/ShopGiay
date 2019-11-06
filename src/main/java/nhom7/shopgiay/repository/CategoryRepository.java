package nhom7.shopgiay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
