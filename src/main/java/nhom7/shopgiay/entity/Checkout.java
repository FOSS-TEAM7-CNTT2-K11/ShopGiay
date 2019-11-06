package nhom7.shopgiay.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the checkout database table.
 * 
 */
@Entity
@Table(name = "checkout")
@NamedQuery(name = "Checkout.findAll", query = "SELECT c FROM Checkout c")
public class Checkout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private boolean confirm;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int status;

	private int type;

	// bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy = "checkout")
	private List<CartItem> cartItems;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	public Checkout() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getConfirm() {
		return this.confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setCheckout(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setCheckout(null);

		return cartItem;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}