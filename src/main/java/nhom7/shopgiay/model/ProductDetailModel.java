package nhom7.shopgiay.model;

import nhom7.shopgiay.entity.Color;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.entity.Size;

public class ProductDetailModel {

	private long id;
	private Product product;
	private Color color;
	private Size size;
	private int amount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
