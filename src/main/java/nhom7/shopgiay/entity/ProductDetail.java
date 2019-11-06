package nhom7.shopgiay.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the product_detail database table.
 * 
 */
@Entity
@Table(name = "product_detail")
@NamedQuery(name = "ProductDetail.findAll", query = "SELECT p FROM ProductDetail p")
public class ProductDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int amount;

	// bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY)
	private List<CartItem> cartItems;

	// bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name = "color_id", referencedColumnName = "id")
	private Color color;

	// bi-directional many-to-one association to Size
	@ManyToOne
	@JoinColumn(name = "size_id", referencedColumnName = "id")
	private Size size;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public ProductDetail() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setProductDetail(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setProductDetail(null);

		return cartItem;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}