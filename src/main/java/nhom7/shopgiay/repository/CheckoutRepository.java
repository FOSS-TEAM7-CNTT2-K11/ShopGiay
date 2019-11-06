package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Checkout;

@Repository
@Transactional
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	@Query(value = "select ck from Checkout ck where ck.confirm = false")
	public List<Checkout> getWaitingCheckouts();

	@Query(value = "select ck from Checkout ck where ck.status = 0 and ck.confirm = true")
	public List<Checkout> getCompletingCheckouts();

	@Query(value = "select ck from Checkout ck where ck.status = 1")
	public List<Checkout> getCompletedCheckouts();

	@Query(value = "select ck from Checkout ck where ck.status = 2")
	public List<Checkout> getDenyedCheckouts();
	
	@Query(value = "select ck from Checkout ck where ck.account.id = :accId")
	public List<Checkout> getByAccountId(@Param("accId") long accountId);
}
