package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.CartItem;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query(value = "select ci from CartItem ci where ci.checkout.id = :checkoutId and show = true")
	public List<CartItem> getWaitingItemsByCheckoutId(@Param("checkoutId") long checkoutId);

	@Query(value = "select ci from CartItem ci where ci.account.id = :accountId")
	public List<CartItem> getByAccountId(@Param("accountId") long accountId);

	@Query(value = "select ci from CartItem ci where ci.checkout.id = :checkoutId")
	public List<CartItem> getsByCheckoutId(@Param("checkoutId") long checkoutId);
	
	@Query(value = "select ci from CartItem ci where ci.account.id = :accountId and ci.show=true and ci.checkout = null")
	List<CartItem> getWaitingItemsByAccountId(@Param("accountId") long accountId);

}
