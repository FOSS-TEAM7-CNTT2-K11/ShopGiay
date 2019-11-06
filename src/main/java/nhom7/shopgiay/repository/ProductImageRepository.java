package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Propagation;
=======
>>>>>>> branch 'master' of https://github.com/fossn7/shopgiay.git
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.ProductImage;

@Repository
<<<<<<< HEAD
@Transactional(propagation = Propagation.REQUIRED)
=======
@Transactional
>>>>>>> branch 'master' of https://github.com/fossn7/shopgiay.git
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

	@Query(value = "select pi from ProductImage pi where pi.product.id=:productId")
	List<ProductImage> getByProductId(@Param("productId") long id);
	
	@Query(nativeQuery = true,  value = "delete i from product_detail as i where i.product_id=:productId")
	@Modifying
	void clearByProdudctId(@Param("productId") long id);
}
