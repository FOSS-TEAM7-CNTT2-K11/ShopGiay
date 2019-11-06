package nhom7.shopgiay.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the cart_item database table.
 * 
 */
@Entity
@Table(name = "cart_item")
@NamedQuery(name = "CartItem.findAll", query = "SELECT c FROM CartItem c")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int amount;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean show;

	// bi-directional many-to-one association to Checkout
	@ManyToOne
	@JoinColumn(name = "checkout_id", referencedColumnName = "id")
	private Checkout checkout;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	// bi-directional many-to-one association to ProductDetail
	@ManyToOne
	@JoinColumn(name = "product_detail_id", referencedColumnName = "id")
	private ProductDetail productDetail;

	public CartItem() {
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getShow() {
		return this.show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Checkout getCheckout() {
		return this.checkout;
	}

	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ProductDetail getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

}