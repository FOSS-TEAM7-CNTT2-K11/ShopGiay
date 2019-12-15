package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Color;
import nhom7.shopgiay.entity.ProductDetail;
import nhom7.shopgiay.entity.Size;

@Repository
@Transactional
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

	@Query(value = "select pd from ProductDetail pd where pd.product.id=:id")
	public List<ProductDetail> getByProductId(@Param("id") long id);
	
	@Query(nativeQuery = true,  value = "delete i from product_detail as i where i.product_id=:productId")
	@Modifying
	void clearByProductId(@Param("productId") long id);
	
	@Query(value = "select pd from ProductDetail pd where pd.size = :size and pd.product.id = :productId and pd.color = :color")
	public ProductDetail getByColorAndSizeAndProductId(@Param("size") Size size, @Param("color") Color color, @Param("productId") long productId);
}
