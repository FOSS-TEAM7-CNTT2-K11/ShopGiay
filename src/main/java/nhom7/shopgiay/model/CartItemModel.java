package nhom7.shopgiay.model;

import java.text.NumberFormat;

import nhom7.shopgiay.entity.CartItem;

public class CartItemModel {

	private long id;
	private int amountSale;
	private String color;
	private String size;
	private String productImage;
	private String productName;
	private int price;
	private int salePrice;
	private String realPrice;
	
	public CartItemModel(CartItem c) {
		id = c.getId();
		amountSale = c.getAmount();
		color = c.getProductDetail().getColor().getColor();
		size = c.getProductDetail().getSize().getSize();
		productImage = c.getProductDetail().getProduct().getThumbnail();
		productName = c.getProductDetail().getProduct().getName();
		price = c.getProductDetail().getProduct().getPrice();
		salePrice =c.getProductDetail().getProduct().getSalePrice();
		realPrice  = c.getProductDetail().getProduct().getRealPrice();
	}

	public String getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAmountSale() {
		return amountSale;
	}
	public void setAmountSale(int amountSale) {
		this.amountSale = amountSale;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	
	public String getTotalPrice() {
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		return myFormat.format((int) amountSale * price * (100 - salePrice) / 100);
	}
	
	public int getTotalPriceUnFormat() {
		return (int) amountSale * price * (100 - salePrice) / 100;
	}
	
	
	
}
