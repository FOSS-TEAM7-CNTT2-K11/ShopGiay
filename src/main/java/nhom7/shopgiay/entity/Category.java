package nhom7.shopgiay.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private String name;

	// bi-directional many-to-many association to Product
	@ManyToMany(mappedBy = "categories")
	private List<Product> products;

	public Category() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public boolean equals(Object obj) {
		Category cat = (Category) obj;
		return this.id == cat.id;
	}

}