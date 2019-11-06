package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.ProductDetail;

@Repository
@Transactional
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

	@Query(value = "select pd from ProductDetail pd where pd.product.id=:id")
	public List<ProductDetail> getByProductId(@Param("id") long id);
	
}
