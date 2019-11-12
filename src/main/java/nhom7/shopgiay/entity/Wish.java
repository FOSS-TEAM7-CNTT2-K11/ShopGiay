package nhom7.shopgiay.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "wish")
@NamedQuery(name = "Wish.findAll", query = "SELECT w FROM Wish w")
public class Wish implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WishPK id;

	@ManyToOne
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
