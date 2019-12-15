package nhom7.shopgiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom7.shopgiay.entity.Wish;
import nhom7.shopgiay.entity.WishPK;

@Repository
@Transactional
public interface WishRepository extends JpaRepository<Wish, WishPK> {

	@Query(value = "select w from Wish w where w.account.id = :accId")
	public List<Wish> getsByAccountId(@Param("accId") long accId);
}
