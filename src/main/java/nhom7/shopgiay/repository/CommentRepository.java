package nhom7.shopgiay.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nhom7.shopgiay.entity.Comment;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value = "select c from Comment c where c.account.id = :accountId")
	public List<Comment> getsByAccountId(@Param("accountId") long accountId);
	
	@Query(value = "select c from Comment c where c.product.id = :productId")
	public List<Comment> getsByProductId(@Param("productId") long productId);
}
