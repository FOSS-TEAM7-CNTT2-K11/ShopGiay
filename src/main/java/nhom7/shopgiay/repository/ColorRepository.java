package nhom7.shopgiay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Color;

@Repository
@Transactional
public interface ColorRepository extends JpaRepository<Color, Long> {

	@Query(value = "select color from Color color where color.color=:color")
	Color findByColor(@Param("color") String color);

}
