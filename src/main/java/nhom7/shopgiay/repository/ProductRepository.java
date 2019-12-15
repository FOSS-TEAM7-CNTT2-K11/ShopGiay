package nhom7.shopgiay.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

	// get newest product
	@Query(value = "select p from Product p")
	public List<Product> getNewestProduct(Pageable page);
	
	
	@Query(value = "select p from Product p")
	public List<Product> abc(Pageable page);
}
