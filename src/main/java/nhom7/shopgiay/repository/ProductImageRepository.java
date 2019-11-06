package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.ProductImage;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

	@Query(value = "select pi from ProductImage pi where pi.product.id=:productId")
	List<ProductImage> getByProductId(@Param("productId") long id);
	
	
}
