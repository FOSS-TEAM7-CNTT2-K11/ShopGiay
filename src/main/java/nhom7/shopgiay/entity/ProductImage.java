package nhom7.shopgiay.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the product_image database table.
 * 
 */
@Entity
@Table(name = "product_image")
@NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
public class ProductImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String image;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public ProductImage() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}